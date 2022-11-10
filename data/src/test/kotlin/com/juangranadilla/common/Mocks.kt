package com.juangranadilla.common

import com.juangranadilla.data.local.entity.FlightEntity
import com.juangranadilla.data.local.getCreationDay
import com.juangranadilla.data.remote.response.CoolFlightRemoteObject
import com.juangranadilla.data.remote.response.CoolFlightsResponse
import com.juangranadilla.data.remote.response.CountryRemoteObject
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import java.util.*
import kotlin.random.Random

fun generateFlights(numberOfFlights: Int = 5) = mutableListOf<Flight>().apply {
    for (id in 1..numberOfFlights) {
        add(generateFlight("$id"))
    }
}

fun generateFlight(id: String) = Flight(
    id,
    City(
        "cityFrom${Random.nextInt()}",
        "countryFrom${Random.nextInt()}",
        "countryCodeFrom${Random.nextInt()}",
        City.CITY_IMAGE_BASE_URL + "cityFromId${Random.nextInt()}" + City.CITY_IMAGE_EXTENSION
    ),
    City(
        "cityTo${Random.nextInt()}",
        "countryTo${Random.nextInt()}",
        "countryCodeTo${Random.nextInt()}",
        City.CITY_IMAGE_BASE_URL + "cityToId${Random.nextInt()}" + City.CITY_IMAGE_EXTENSION
    ),
    "${Random.nextFloat()} €",
    Calendar.getInstance(),
    Calendar.getInstance().apply { add(Calendar.MONTH, 1) },
    "${Random.nextFloat()} hours"
)

fun generateLocalFlights(numberOfFlights: Int = 5) = mutableListOf<FlightEntity>().apply {
    for (id in 1..numberOfFlights) {
        add(
            FlightEntity(
                "$id",
                "cityFrom${Random.nextInt()}",
                "countryCodeFrom${Random.nextInt()}",
                "countryFrom${Random.nextInt()}",
                City.CITY_IMAGE_BASE_URL + "cityFromId${Random.nextInt()}" + City.CITY_IMAGE_EXTENSION,
                "cityTo${Random.nextInt()}",
                "countryCodeTo${Random.nextInt()}",
                "countryTo${Random.nextInt()}",
                City.CITY_IMAGE_BASE_URL + "cityToId${Random.nextInt()}" + City.CITY_IMAGE_EXTENSION,
                "${Random.nextFloat()} €",
                Calendar.getInstance().timeInMillis,
                Calendar.getInstance().apply { add(Calendar.MONTH, 1) }.timeInMillis,
                "${Random.nextFloat()} hours",
                getCreationDay()
            )
        )
    }
}

fun generateFlightsRemoteResponse() = CoolFlightsResponse(
    "1234",
    "EUR",
    1.0f,
    generateRemoteFlights()
)

private fun generateRemoteFlights(numberOfFlights: Int = 45) =
    mutableListOf<CoolFlightRemoteObject>().apply {
        for (id in 1..numberOfFlights)
            add(
                CoolFlightRemoteObject(
                    id.toString(),
                    "Origin${Random.nextInt()}",
                    "Destination${Random.nextInt()}",
                    "OriginCity${Random.nextInt()}",
                    "OriginCityCode${Random.nextInt()}",
                    "DestinationCity${Random.nextInt()}",
                    "DestinationCityCode${Random.nextInt()}",
                    CountryRemoteObject(Random.nextInt().toString(), "Country${Random.nextInt()}"),
                    CountryRemoteObject(Random.nextInt().toString(), "Country${Random.nextInt()}"),
                    "fromId",
                    "toId",
                    1668013607,
                    1668013666,
                    1668014607,
                    1668014677,
                    Random.nextFloat(),
                    Random.nextInt().toString()
                )
            )
    }