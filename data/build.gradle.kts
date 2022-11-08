import com.juangranadilla.buildsrc.Lib
import com.juangranadilla.buildsrc.TestLib

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":domain"))

    implementation(Lib.coroutines)
    implementation(Lib.koin)
    implementation(Lib.retrofit)
    implementation(Lib.retrofit_gson_converter)
    implementation(Lib.okhttp3_logging_interceptor)
    implementation(Lib.room_common)

    implementation(TestLib.mockito)
    testImplementation(TestLib.junit)
    testImplementation(TestLib.kotlin_test)
    testImplementation(TestLib.kotlin_coroutines_test)
}