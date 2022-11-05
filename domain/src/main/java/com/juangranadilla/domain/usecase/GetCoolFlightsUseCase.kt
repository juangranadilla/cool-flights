package com.juangranadilla.domain.usecase

import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.repository.FlightsRepository
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.flow.Flow

interface GetCoolFlightsUseCase {
    operator fun invoke(): Flow<DataState<List<Flight>>>
}

class GetCoolFlightsUseCaseImpl(
    private val repository: FlightsRepository
) : GetCoolFlightsUseCase {

    override fun invoke(): Flow<DataState<List<Flight>>> {
        return repository.getCoolFlights()
    }
}