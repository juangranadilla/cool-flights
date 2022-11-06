package com.juangranadilla.domain.model

data class City(
    val name: String,
    val countryName: String,
    val countryCode: String
) {
    companion object {
        private const val CITY_IMAGE_BASE_URL = "https://images.kiwi.com/photos/600x330/"
        private const val CITY_IMAGE_EXTENSION = ".jpg"
    }

    fun getImageUrl() =
        "$CITY_IMAGE_BASE_URL${name.lowercase()}_${countryCode.lowercase()}$CITY_IMAGE_EXTENSION"
}