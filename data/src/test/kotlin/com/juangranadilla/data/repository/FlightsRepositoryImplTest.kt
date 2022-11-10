package com.juangranadilla.data.repository

import com.juangranadilla.common.BaseDataTest
import com.juangranadilla.common.generateFlights
import com.juangranadilla.data.local.FlightsLocalDataSource
import com.juangranadilla.data.remote.FlightsRemoteDataSource
import com.juangranadilla.domain.repository.FlightsRepository
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class FlightsRepositoryImplTest : BaseDataTest() {

    @Mock
    private lateinit var remote: FlightsRemoteDataSource

    @Mock
    private lateinit var local: FlightsLocalDataSource

    private lateinit var repository: FlightsRepository

    @Before
    fun setup() {
        repository = FlightsRepositoryImpl(remote, local)
    }

    @Test
    fun `If local database has flights for today, we emit those flights`() = runTest {
        whenever(local.getCoolFlights()).thenReturn(DataState.Success(generateFlights(5)))

        val flightsFlow = repository.getCoolFlights().toList()

        verify(local).getCoolFlights()
        verifyNoMoreInteractions(local)
        verifyNoInteractions(remote)
        assert(flightsFlow.first() is DataState.Loading)
        assert(flightsFlow[1] is DataState.Success)
        assert((flightsFlow[1] as DataState.Success).value.size == 5)
    }

    @Test
    fun `If local database does not have flights for today, we ask for remote flights and save them in database`() =
        runTest {
            whenever(local.getCoolFlights()).thenReturn(DataState.Success(emptyList()))
            whenever(remote.getCoolFlights()).thenReturn(DataState.Success(generateFlights(5)))
            whenever(local.checkIfFlightExists(any())).thenReturn(false)

            val flightsFlow = repository.getCoolFlights().toList()

            verify(local).getCoolFlights()
            verify(remote).getCoolFlights()
            verify(local, times(5)).checkIfFlightExists(any())
            verify(local, times(5)).saveFlight(any())
            verifyNoMoreInteractions(local)
            verifyNoMoreInteractions(remote)
            assert(flightsFlow.first() is DataState.Loading)
            assert(flightsFlow[1] is DataState.Success)
            assert((flightsFlow[1] as DataState.Success).value.size == 5)
        }

    @Test
    fun `If data source returns an error, repository should return a DataState error`() = runTest {
        whenever(local.getCoolFlights()).then { throw Throwable() }

        val flightsFlow = repository.getCoolFlights().toList()

        verify(local).getCoolFlights()
        verifyNoMoreInteractions(local)
        verifyNoMoreInteractions(remote)
        assert(flightsFlow.first() is DataState.Loading)
        assert((flightsFlow.first() as DataState.Loading).isLoading)
        assert(flightsFlow[1] is DataState.Loading)
        assert(!(flightsFlow[1] as DataState.Loading).isLoading)
        assert(flightsFlow[2] is DataState.Error)
    }
}