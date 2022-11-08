package com.juangranadilla.data.local

import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import java.util.Calendar

interface FlightsLocalDataSource {
    suspend fun getCoolFlights(): DataState<List<Flight>>
    suspend fun checkIfFlightExists(id: String): Boolean
    suspend fun saveFlight(flight: Flight): DataState<Boolean>
}

class FlightsLocalDataSourceImpl(private val database: DatabaseBridge) : FlightsLocalDataSource {

    override suspend fun getCoolFlights(): DataState<List<Flight>> {
        return try {
            DataState.Success(database.getFlightsByDay(getCreationDay()).toModel())
        } catch (throwable: Throwable) {
            DataState.Error("Error getting flights from database", throwable)
        }
    }

    override suspend fun checkIfFlightExists(id: String): Boolean {
        return database.checkIfFlightExists(id)
    }

    override suspend fun saveFlight(flight: Flight): DataState<Boolean> {
        return try {
            database.insertFlight(flight.toEntity(getCreationDay()))
            DataState.Success(true)
        } catch (throwable: Throwable) {
            DataState.Error("Error inserting flights into database", throwable)
        }
    }

    private fun getCreationDay(): String = "${
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    }_${
        Calendar.getInstance().get(Calendar.YEAR)
    }"
}