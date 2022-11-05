package com.juangranadilla.data.repository

import com.juangranadilla.data.local.FlightsLocalDataSource
import com.juangranadilla.data.remote.FlightsRemoteDataSource
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.repository.FlightsRepository
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlightsRepositoryImpl(
    private val remote: FlightsRemoteDataSource,
    private val local: FlightsLocalDataSource
) : FlightsRepository {

    override fun getCoolFlights(): Flow<DataState<List<Flight>>> = flow {
        // TODO add logic to get maximum 5 random flights for a day (should not be repeated)
        emit(DataState.Loading(true))
        val allFlights = remote.getCoolFlights()
        emit(DataState.Success(allFlights.subList(0, 5)))
    }
}