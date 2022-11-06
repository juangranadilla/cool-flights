package com.juangranadilla.buildsrc

object AndroidSdk {
    const val minSdk = 23
    const val targetSdk = 33
    const val compileSdk = 33
    const val applicationId = "com.juangranadilla.coolflights"
    const val versionCode = 1
    const val versionName = "1.0"
}

object Lib {

    object Version {
        const val core_ktx_version = "1.9.0"
        const val lifecycle_version = "2.5.1"
        const val coroutines_version = "1.3.9"
        const val koin_version = "3.2.0"
        const val retrofit_version = "2.9.0"
        const val okhttp3_version = "4.10.0"
        const val coil_version = "2.2.2"
        const val activity_compose_version = "1.6.1"
        const val orbit_version = "0.21.0"
        const val compose_viewmodel = "2.5.1"
    }

    const val core_ktx = "androidx.core:core-ktx:${Version.core_ktx_version}"
    const val compose_bom = "androidx.compose:compose-bom:2022.10.00"
    const val compose_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_tooling_preview = "androidx.compose.ui:ui-tooling"
    const val compose_livedata = "androidx.compose.runtime:runtime-livedata"
    const val compose_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.compose_viewmodel}"
    const val activity_compose = "androidx.activity:activity-compose:${Version.activity_compose_version}"
    const val orbit_compose_ui = "kiwi.orbit.compose:ui:${Version.orbit_version}"
    const val orbit_compose_icons = "kiwi.orbit.compose:icons:${Version.orbit_version}"
    const val orbit_compose_illustrations = "kiwi.orbit.compose:illustrations:${Version.orbit_version}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle_version}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle_version}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines_version}"
    const val koin = "io.insert-koin:koin-core:${Version.koin_version}"
    const val koin_android = "io.insert-koin:koin-android:${Version.koin_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit_version}"
    const val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Version.retrofit_version}"
    const val okhttp3_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3_version}"
    const val coil_compose = "io.coil-kt:coil-compose:${Version.coil_version}"
}

object TestLib {

    object Version {
        const val junit_version = "4.13.2"
        const val mockito_version = "4.8.1"
        const val kotlin_version = "1.7.20"
        const val kotlin_coroutines_test_version = "1.6.4"
    }

    const val junit = "junit:junit:${Version.junit_version}"
    const val mockito = "org.mockito:mockito-core:${Version.mockito_version}"
    const val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:${Version.kotlin_version}"
    const val kotlin_coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.kotlin_coroutines_test_version}"
}