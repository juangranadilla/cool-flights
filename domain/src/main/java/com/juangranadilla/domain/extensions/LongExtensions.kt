package com.juangranadilla.domain.extensions

import java.util.*

fun Long.getCalendar(): Calendar = Calendar.getInstance().apply { time = toDate() }

fun Long.toDate() = Date(this * 1000)