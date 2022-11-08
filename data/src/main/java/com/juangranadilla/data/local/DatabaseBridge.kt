package com.juangranadilla.data.local

import com.juangranadilla.data.local.entity.FlightEntity

interface DatabaseBridge {
    suspend fun getFlightsByDay(creationDay: String): List<FlightEntity>
    suspend fun checkIfFlightExists(id: String): Boolean
    suspend fun insertFlight(flight: FlightEntity)
}