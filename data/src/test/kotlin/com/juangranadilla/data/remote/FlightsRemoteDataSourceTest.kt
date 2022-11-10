package com.juangranadilla.data.remote

import com.juangranadilla.common.BaseDataTest
import com.juangranadilla.common.generateFlightsRemoteResponse
import com.juangranadilla.data.remote.mapper.getCurrencySymbol
import com.juangranadilla.domain.extensions.getCalendarFromUnix
import com.juangranadilla.domain.extensions.getFormattedDate
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.result.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class FlightsRemoteDataSourceTest : BaseDataTest() {

    @Mock
    private lateinit var service: FlightsService

    private lateinit var flightsRemoteDataSource: FlightsRemoteDataSource

    private val generatedResponse = generateFlightsRemoteResponse()

    @Before
    fun setup() {
        flightsRemoteDataSource = FlightsRemoteDataSourceImpl(service)
    }

    @Test
    fun `Having a successful response, return a successful DataState with mapped data`() = runTest {
        whenever(
            service.getCoolFlights(
                any(), any(), any(), any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(), any(), any(), any(), any()
            )
        ).thenReturn(Response.success(generatedResponse))

        val dataState = flightsRemoteDataSource.getCoolFlights()

        verifyCallToService()
        assert(dataState is DataState.Success)
        assert((dataState as DataState.Success).value.size == generatedResponse.data.size)
        dataState.value.zip(generatedResponse.data) { flight, remoteFlight ->
            assert(flight.id == remoteFlight.id)
            assert(flight.from.name == remoteFlight.cityFrom)
            assert(flight.from.countryName == remoteFlight.countryFrom.name)
            assert(flight.from.countryCode == remoteFlight.countryFrom.code)
            assert(flight.from.imageUrl ==
                    City.CITY_IMAGE_BASE_URL + remoteFlight.mapIdfrom + City.CITY_IMAGE_EXTENSION)
            assert(flight.to.name == remoteFlight.cityTo)
            assert(flight.to.countryName == remoteFlight.countryTo.name)
            assert(flight.to.countryCode == remoteFlight.countryTo.code)
            assert(flight.to.imageUrl ==
                    City.CITY_IMAGE_BASE_URL + remoteFlight.mapIdto + City.CITY_IMAGE_EXTENSION)
            assert(
                flight.price == "%,.2f".format(remoteFlight.price) + " ${
                    getCurrencySymbol(
                        generatedResponse.currency
                    )
                }"
            )
            assert(flight.departure == remoteFlight.dTimeUTC.getCalendarFromUnix())
            assert(flight.arrival == remoteFlight.aTimeUTC.getCalendarFromUnix())
            assert(flight.duration == remoteFlight.fly_duration)
        }
    }

    @Test
    fun `Having a response error, return a DataState error`() = runTest {
        whenever(
            service.getCoolFlights(
                any(), any(), any(), any(), any(), any(), any(), any(), any(),
                any(), any(), any(), any(), any(), any(), any(), any(), any()
            )
        ).thenReturn(Response.error(400, byteArrayOf().toResponseBody()))

        val dataState = flightsRemoteDataSource.getCoolFlights()

        verifyCallToService()
        assert(dataState is DataState.Error)
    }

    private fun verifyCallToService() = runTest {
        verify(service).getCoolFlights(
            "3",
            "popularity",
            0,
            "en",
            0,
            0,
            "49.2-16.61-250km",
            "anywhere",
            "aggregateResults",
            Calendar.getInstance().timeInMillis.getFormattedDate(),
            Calendar.getInstance().apply { add(Calendar.MONTH, 1) }.timeInMillis.getFormattedDate(),
            "oneway",
            0,
            1,
            0,
            1,
            45,
            "skypicker-android"
        )
    }
}