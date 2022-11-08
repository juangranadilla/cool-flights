package com.juangranadilla.data.repository

import com.juangranadilla.data.local.FlightsLocalDataSource
import com.juangranadilla.data.remote.FlightsRemoteDataSource
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.repository.FlightsRepository
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class FlightsRepositoryImpl(
    private val remote: FlightsRemoteDataSource,
    private val local: FlightsLocalDataSource
) : FlightsRepository {

    companion object {
        private const val NUMBER_OF_RANDOM_FLIGHTS = 5
    }

    override fun getCoolFlights(): Flow<DataState<List<Flight>>> = flow {
        try {
            emit(DataState.Loading(true))

            local.getCoolFlights().takeIf {
                it is DataState.Success && it.value.isNotEmpty()
            }?.let {
                emit(it)
            } ?: (remote.getCoolFlights() as? DataState.Success)?.value?.run {
                val randomFlights = generateRandomFlights(local)
                emit(DataState.Success(randomFlights))
            }

        } catch (throwable: Throwable) {
            emit(DataState.Loading(false))
            emit(DataState.Error("Error getting cool flights from repository", throwable))
        }
    }

    private suspend fun List<Flight>.generateRandomFlights(
        local: FlightsLocalDataSource
    ): List<Flight> {
        val randomFlights = mutableListOf<Flight>()

        for (item in this) {
            get(Random.nextInt(size)).takeIf { !local.checkIfFlightExists(it.id) }?.let {
                randomFlights.add(it)
                local.saveFlight(it)
            }
            if (randomFlights.size == NUMBER_OF_RANDOM_FLIGHTS) {
                break
            }
        }

        return randomFlights
    }
}