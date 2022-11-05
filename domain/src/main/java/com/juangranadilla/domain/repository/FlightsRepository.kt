package com.juangranadilla.domain.repository

import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun getCoolFlights(): Flow<DataState<List<Flight>>>
}