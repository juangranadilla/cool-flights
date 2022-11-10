package com.juangranadilla.domain.model

data class City(
    val name: String,
    val countryName: String,
    val countryCode: String,
    val imageUrl: String
) {
    companion object {
        const val CITY_IMAGE_BASE_URL = "https://images.kiwi.com/photos/600x330/"
        const val CITY_IMAGE_EXTENSION = ".jpg"
    }
}