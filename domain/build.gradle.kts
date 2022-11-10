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
    implementation(Lib.coroutines)
    implementation(Lib.koin)

    implementation(TestLib.mockito_kotlin)
    testImplementation(TestLib.junit)
    testImplementation(TestLib.kotlin_test)
    testImplementation(TestLib.kotlin_coroutines_test)
}