package com.juangranadilla.room

import com.juangranadilla.data.local.DatabaseBridge
import com.juangranadilla.data.local.entity.FlightEntity

class DatabaseBridgeImpl(private val database: FlightsDatabase) : DatabaseBridge {

    override suspend fun getFlightsByDay(creationDay: String): List<FlightEntity> {
        return database.flightsDao().getFlightsByDay(creationDay)
    }

    override suspend fun checkIfFlightExists(id: String): Boolean {
        return database.flightsDao().checkIfFlightExists(id)
    }

    override suspend fun insertFlight(flight: FlightEntity) {
        return database.flightsDao().insertFlight(flight)
    }
}