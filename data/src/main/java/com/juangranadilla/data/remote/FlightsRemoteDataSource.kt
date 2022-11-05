package com.juangranadilla.data.remote

import com.juangranadilla.data.remote.mapper.toModel
import com.juangranadilla.domain.model.Flight
import java.text.SimpleDateFormat
import java.util.*

interface FlightsRemoteDataSource {
    suspend fun getCoolFlights(
        version: String = "3",
        sort: String = "popularity",
        asc: Int = 0,
        locale: String = "en",
        children: Int = 0,
        infants: Int = 0,
        flyFrom: String = "49.2-16.61-250km",
        flyTo: String = "anywhere",
        featureName: String = "aggregateResults",
        departAfter: String = getFormattedDate(Calendar.getInstance().timeInMillis),
        departBefore: String = getFormattedDate(
            Calendar.getInstance().apply { add(Calendar.MONTH, 1) }.timeInMillis
        ),
        flightType: String = "oneway",
        onePerDate: Int = 0,
        oneForCity: Int = 1,
        waitForRefresh: Int = 0,
        adults: Int = 1,
        limit: Int = 45,
        partner: String = "skypicker-android"
    ): List<Flight>
}

class FlightsRemoteDataSourceImpl(
    private val service: FlightsService
) : FlightsRemoteDataSource {

    override suspend fun getCoolFlights(
        version: String,
        sort: String,
        asc: Int,
        locale: String,
        children: Int,
        infants: Int,
        flyFrom: String,
        flyTo: String,
        featureName: String,
        departAfter: String,
        departBefore: String,
        flightType: String,
        onePerDate: Int,
        oneForCity: Int,
        waitForRefresh: Int,
        adults: Int,
        limit: Int,
        partner: String
    ): List<Flight> {
        return service.getCoolFlights(
            version,
            sort,
            asc,
            locale,
            children,
            infants,
            flyFrom,
            flyTo,
            featureName,
            departAfter,
            departBefore,
            flightType,
            onePerDate,
            oneForCity,
            waitForRefresh,
            adults,
            limit,
            partner
        ).toModel()
    }
}

private fun getFormattedDate(time: Long): String = SimpleDateFormat(
    "dd-mm-yyyyThh:mm",
    Locale.ENGLISH
).format(time)