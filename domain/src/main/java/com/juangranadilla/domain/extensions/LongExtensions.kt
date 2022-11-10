package com.juangranadilla.domain.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.getCalendarFromMilliSeconds(): Calendar =
    Calendar.getInstance().apply { time = fromMilliSecondsToDate() }

fun Long.getCalendarFromUnix(): Calendar = Calendar.getInstance().apply { time = fromUnixToDate() }

fun Long.fromMilliSecondsToDate() = Date(this)

fun Long.fromUnixToDate() = Date(this * 1000)

fun Long.getFormattedDate(): String = SimpleDateFormat(
    "dd/MM/yyyy",
    Locale.ENGLISH
).format(this)