package com.juangranadilla.domain.model

import java.util.*

data class Flight(
    val id: String,
    val from: City,
    val to: City,
    val price: String,
    val departure: Calendar,
    val arrival: Calendar,
    val duration: String
)