package com.juangranadilla.domain.extensions

import java.text.Normalizer

fun String.removeAccents(): String =
    Regex("\\p{InCombiningDiacriticalMarks}+")
        .replace(Normalizer.normalize(this, Normalizer.Form.NFD), "")

fun String.getStringBeforeComma() = split(",")[0].replace(",", "")