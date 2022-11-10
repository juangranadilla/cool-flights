package com.juangranadilla.data.remote.mapper

import com.juangranadilla.data.remote.response.CoolFlightRemoteObject
import com.juangranadilla.data.remote.response.CoolFlightsResponse
import com.juangranadilla.domain.extensions.getCalendarFromUnix
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import java.util.*

fun CoolFlightsResponse.toModel(): List<Flight> = data.map { it.toModel(currency) }

fun CoolFlightRemoteObject.toModel(currency: String) = Flight(
    id,
    City(
        cityFrom,
        countryFrom.name,
        countryFrom.code,
        "${City.CITY_IMAGE_BASE_URL}$mapIdfrom${City.CITY_IMAGE_EXTENSION}"
    ),
    City(
        cityTo,
        countryTo.name,
        countryTo.code,
        "${City.CITY_IMAGE_BASE_URL}$mapIdto${City.CITY_IMAGE_EXTENSION}"
    ),
    "%,.2f".format(price) + " ${getCurrencySymbol(currency)}",
    dTimeUTC.getCalendarFromUnix(),
    aTimeUTC.getCalendarFromUnix(),
    fly_duration
)

fun getCurrencySymbol(currencyCode: String): String = Currency.getInstance(currencyCode).symbol