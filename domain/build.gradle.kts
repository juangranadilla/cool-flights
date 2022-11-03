import com.juangranadilla.buildsrc.Lib

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Lib.coroutines)
    implementation(Lib.koin)

    implementation(com.juangranadilla.buildsrc.TestLib.mockito)
    testImplementation(com.juangranadilla.buildsrc.TestLib.junit)
    testImplementation(com.juangranadilla.buildsrc.TestLib.kotlin_test)
    testImplementation(com.juangranadilla.buildsrc.TestLib.kotlin_coroutines_test)
}