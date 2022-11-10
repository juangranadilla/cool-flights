package com.juangranadilla.data.local

import com.juangranadilla.data.local.entity.FlightEntity
import com.juangranadilla.domain.extensions.getCalendarFromMilliSeconds
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight

fun Flight.toEntity(creationDay: String) = FlightEntity(
    id,
    from.name,
    from.countryCode,
    from.countryName,
    from.imageUrl,
    to.name,
    to.countryCode,
    to.countryName,
    to.imageUrl,
    price,
    departure.timeInMillis,
    arrival.timeInMillis,
    duration,
    creationDay
)

fun FlightEntity.toModel() = Flight(
    id,
    City(cityFrom, countryNameFrom, countryCodeFrom, imageUrlFrom),
    City(cityTo, countryNameTo, countryCodeTo, imageUrlTo),
    price,
    departureTime.getCalendarFromMilliSeconds(),
    arrivalTime.getCalendarFromMilliSeconds(),
    duration
)

fun List<Flight>.toEntity(creationDay: String) = map { it.toEntity(creationDay) }

fun List<FlightEntity>.toModel() = map { it.toModel() }