package com.juangranadilla.domain.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.getHour(): String = SimpleDateFormat("K:mm a", Locale.ENGLISH).format(time)

fun Calendar.getDayAndMonthFormatted(): String =
    SimpleDateFormat("dd. MMMM", Locale.ENGLISH).format(time)

fun Calendar.getHourDayAndMonthFormatted(): String =
    SimpleDateFormat("dd. MMMM K:mm a", Locale.ENGLISH).format(time)