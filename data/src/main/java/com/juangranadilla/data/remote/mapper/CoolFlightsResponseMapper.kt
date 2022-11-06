package com.juangranadilla.data.remote.mapper

import com.juangranadilla.data.remote.response.CoolFlightRemoteObject
import com.juangranadilla.data.remote.response.CoolFlightsResponse
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import java.util.*

fun CoolFlightsResponse.toModel(): List<Flight> = data.map { it.toModel(currency) }

fun CoolFlightRemoteObject.toModel(currency: String) = Flight(
    id,
    City(cityFrom, countryFrom.name, countryFrom.code),
    City(cityTo, countryTo.name, countryTo.code),
    "%,.2f".format(price) + " ${getCurrencySymbol(currency)}",
    dTime.getCalendar(),
    aTime.getCalendar(),
    fly_duration
)

private fun Long.getCalendar(): Calendar = Calendar.getInstance().apply { time = toDate() }

private fun Long.toDate() = Date(this * 1000)

private fun getCurrencySymbol(currencyCode: String) = Currency.getInstance(currencyCode).symbol