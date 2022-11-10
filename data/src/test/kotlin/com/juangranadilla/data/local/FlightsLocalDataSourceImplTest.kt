package com.juangranadilla.data.local

import com.juangranadilla.common.BaseDataTest
import com.juangranadilla.common.generateFlight
import com.juangranadilla.common.generateLocalFlights
import com.juangranadilla.domain.extensions.getCalendarFromMilliSeconds
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class FlightsLocalDataSourceImplTest : BaseDataTest() {

    @Mock
    private lateinit var database: DatabaseBridge

    private lateinit var localDataSource: FlightsLocalDataSource

    private val localFlights = generateLocalFlights()

    @Before
    fun setup() {
        localDataSource = FlightsLocalDataSourceImpl(database)
    }

    @Test
    fun `Retrieving flights from database, return a successful DataState with mapped data`() =
        runTest {
            val day = getCreationDay()
            whenever(database.getFlightsByDay(day)).thenReturn(localFlights)

            val dataState = localDataSource.getCoolFlights()

            verify(database).getFlightsByDay(day)
            assert(dataState is DataState.Success)
            assert((dataState as DataState.Success).value.size == localFlights.size)
            dataState.value.zip(localFlights) { flight, localFlight ->
                assert(flight.id == localFlight.id)
                assert(flight.from.name == localFlight.cityFrom)
                assert(flight.from.countryName == localFlight.countryNameFrom)
                assert(flight.from.countryCode == localFlight.countryCodeFrom)
                assert(flight.from.imageUrl == localFlight.imageUrlFrom)
                assert(flight.to.name == localFlight.cityTo)
                assert(flight.to.countryName == localFlight.countryNameTo)
                assert(flight.to.countryCode == localFlight.countryCodeTo)
                assert(flight.to.imageUrl == localFlight.imageUrlTo)
                assert(flight.price == localFlight.price)
                assert(flight.departure == localFlight.departureTime.getCalendarFromMilliSeconds())
                assert(flight.arrival == localFlight.arrivalTime.getCalendarFromMilliSeconds())
                assert(flight.duration == localFlight.duration)
            }
        }

    @Test
    fun `Check that a DataState error is returned when receiving an error from database`() =
        runTest {
            val day = getCreationDay()
            whenever(database.getFlightsByDay(day)).then { throw Throwable() }

            val dataState = localDataSource.getCoolFlights()

            verify(database).getFlightsByDay(day)
            assert(dataState is DataState.Error)
        }

    @Test
    fun `If a flight exists in database, return a boolean (true) in a DataState`() = runTest {
        val flightId = "1234"
        whenever(database.checkIfFlightExists(flightId)).thenReturn(true)

        val exists = localDataSource.checkIfFlightExists(flightId)

        verify(database).checkIfFlightExists(flightId)
        assert(exists)
    }

    @Test
    fun `If a flight is saved in database, return a DataState with boolean`() = runTest {
        val day = getCreationDay()
        val flightId = "1234"
        val flight = generateFlight(flightId)

        val dataState = localDataSource.saveFlight(flight)

        verify(database).insertFlight(flight.toEntity(day))
        assert(dataState is DataState.Success)
        assert((dataState as DataState.Success).value)
    }

    @Test
    fun `If a flight is not saved in database and throws an error, return a DataState error`() =
        runTest {
            val day = getCreationDay()
            val flightId = "1234"
            val flight = generateFlight(flightId)
            whenever(database.insertFlight(flight.toEntity(day))).then { throw Throwable() }

            val dataState = localDataSource.saveFlight(flight)

            verify(database).insertFlight(flight.toEntity(day))
            assert(dataState is DataState.Error)
        }
}