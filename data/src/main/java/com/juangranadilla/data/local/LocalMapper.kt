package com.juangranadilla.data.local

import com.juangranadilla.data.local.entity.FlightEntity
import com.juangranadilla.domain.extensions.getCalendar
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight

fun Flight.toEntity(creationDay: String) = FlightEntity(
    id,
    from.name,
    from.countryCode,
    from.countryName,
    to.name,
    to.countryCode,
    to.countryName,
    price,
    departure.timeInMillis,
    arrival.timeInMillis,
    duration,
    creationDay
)

fun FlightEntity.toModel() = Flight(
    id,
    City(cityFrom, countryNameFrom, countryCodeFrom),
    City(cityTo, countryNameTo, countryCodeTo),
    price,
    departureTime.getCalendar(),
    arrivalTime.getCalendar(),
    duration
)

fun List<Flight>.toEntity(creationDay: String) = map { it.toEntity(creationDay) }

fun List<FlightEntity>.toModel() = map { it.toModel() }