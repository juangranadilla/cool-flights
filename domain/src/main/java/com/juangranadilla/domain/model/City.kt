package com.juangranadilla.domain.model

import com.juangranadilla.domain.extensions.getStringBeforeComma
import com.juangranadilla.domain.extensions.removeAccents

data class City(
    val name: String, val countryName: String, val countryCode: String
) {
    companion object {
        private const val CITY_IMAGE_BASE_URL = "https://images.kiwi.com/photos/600x330/"
        private const val CITY_IMAGE_EXTENSION = ".jpg"
    }

    fun getImageUrl() = (CITY_IMAGE_BASE_URL + name.lowercase().getStringBeforeComma()
            + "_" + countryCode.lowercase() + CITY_IMAGE_EXTENSION)
        .trim()
        .removeAccents()
        .replace(" ", "-")
}