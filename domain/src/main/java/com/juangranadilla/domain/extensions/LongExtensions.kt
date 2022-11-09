package com.juangranadilla.domain.extensions

import java.util.*

fun Long.getCalendarFromMilliSeconds() =
    Calendar.getInstance().apply { time = fromMilliSecondsToDate() }

fun Long.getCalendarFromUnix(): Calendar = Calendar.getInstance().apply { time = fromUnixToDate() }

fun Long.fromMilliSecondsToDate() = Date(this)

fun Long.fromUnixToDate() = Date(this * 1000)