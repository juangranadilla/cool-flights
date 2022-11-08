package com.juangranadilla.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class FlightEntity(
    @PrimaryKey
    val id: String,
    val cityFrom: String,
    val countryCodeFrom: String,
    val countryNameFrom: String,
    val cityTo: String,
    val countryCodeTo: String,
    val countryNameTo: String,
    val price: String,
    val departureTime: Long,
    val arrivalTime: Long,
    val duration: String,
    val creationDay: String
)