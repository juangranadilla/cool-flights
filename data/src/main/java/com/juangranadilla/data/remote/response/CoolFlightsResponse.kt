package com.juangranadilla.data.remote.response

data class CoolFlightsResponse(
    val search_id: String,
    val currency: String,
    val fx_rate: Float,
    val data: List<CoolFlightRemoteObject>
)

data class CoolFlightRemoteObject(
    val id: String,
    val flyFrom: String,
    val flyTo: String,
    val cityFrom: String,
    val cityCodeFrom: String,
    val cityTo: String,
    val cityCodeTo: String,
    val countryFrom: CountryRemoteObject,
    val countryTo: CountryRemoteObject,
    val mapIdfrom: String,
    val mapIdto: String,
    val dTime: Long,
    val dTimeUTC: Long,
    val aTime: Long,
    val aTimeUTC: Long,
    val price: Float,
    val fly_duration: String
)

data class CountryRemoteObject(
    val code: String,
    val name: String
)