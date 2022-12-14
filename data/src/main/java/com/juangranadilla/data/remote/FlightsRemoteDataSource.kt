package com.juangranadilla.data.remote

import com.juangranadilla.data.remote.mapper.toModel
import com.juangranadilla.domain.extensions.getFormattedDate
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
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
        departAfter: String = Calendar.getInstance().timeInMillis.getFormattedDate(),
        departBefore: String = Calendar.getInstance().apply {
            add(Calendar.MONTH, 1)
        }.timeInMillis.getFormattedDate(),
        flightType: String = "oneway",
        onePerDate: Int = 0,
        oneForCity: Int = 1,
        waitForRefresh: Int = 0,
        adults: Int = 1,
        limit: Int = 45,
        partner: String = "skypicker-android"
    ): DataState<List<Flight>>
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
    ): DataState<List<Flight>> {
        val response = service.getCoolFlights(
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
        )

        return response.body()?.takeIf { response.isSuccessful }?.let {
            DataState.Success(it.toModel())
        } ?: DataState.Error("Error getting flights response from server")
    }
}