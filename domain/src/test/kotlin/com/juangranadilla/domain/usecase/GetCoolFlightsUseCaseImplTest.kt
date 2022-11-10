package com.juangranadilla.domain.usecase

import com.juangranadilla.common.BaseDomainTest
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.repository.FlightsRepository
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.*

class GetCoolFlightsUseCaseImplTest : BaseDomainTest() {

    @Mock
    private lateinit var repository: FlightsRepository

    private lateinit var useCase: GetCoolFlightsUseCase

    @Before
    fun setup() {
        useCase = GetCoolFlightsUseCaseImpl(repository)
    }

    @Test
    fun `Check that use case calls flights repository to get cool flights`() {
        val dataState = mock<Flow<DataState<List<Flight>>>>()
        whenever(repository.getCoolFlights()).thenReturn(dataState)

        useCase()

        verify(repository).getCoolFlights()
        verifyNoMoreInteractions(repository)
    }
}