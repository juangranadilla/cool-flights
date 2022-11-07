package com.juangranadilla.data.remote

import com.juangranadilla.data.remote.response.CoolFlightsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightsService {

    @GET("/flights")
    suspend fun getCoolFlights(
        @Query("v") version: String,
        @Query("sort") sort: String,
        @Query("asc") asc: Int,
        @Query("locale") locale: String,
        @Query("children") children: Int,
        @Query("infants") infants: Int,
        @Query("fly_from") flyFrom: String,
        @Query("fly_to") flyTo: String,
        @Query("featureName") featureName: String,
        @Query("depart_after") departAfter: String,
        @Query("depart_before") departBefore: String,
        @Query("flight_type") flightType: String,
        @Query("one_per_date") onePerDate: Int,
        @Query("one_for_city") oneForCity: Int,
        @Query("wait_for_refresh") waitForRefresh: Int,
        @Query("adults") adults: Int,
        @Query("limit") limit: Int,
        @Query("partner") partner: String
    ): Response<CoolFlightsResponse>
}