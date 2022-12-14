import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.juangranadilla.buildsrc.AndroidSdk
import com.juangranadilla.buildsrc.Lib
import com.juangranadilla.buildsrc.TestLib

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidSdk.compileSdk
    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = AndroidSdk.applicationId

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    implementation(project(":domain"))

    /*
     * Presentation layer should not depend on data layer, but it is needed when using Koin
     * as dependency injection framework, because it creates the graph in :app Application class
      */
    implementation(project(":data"))

    val composeBom = platform(Lib.compose_bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(Lib.core_ktx)
    implementation(Lib.compose_preview)
    implementation(Lib.compose_tooling_preview)
    implementation(Lib.compose_livedata)
    implementation(Lib.compose_viewmodel)
    implementation(Lib.activity_compose)
    implementation(Lib.orbit_compose_ui)
    implementation(Lib.orbit_compose_icons)
    implementation(Lib.orbit_compose_illustrations)
    implementation(Lib.accompanist_system_controller)
    implementation(Lib.livedata)
    implementation(Lib.viewmodel)
    implementation(Lib.coroutines)
    implementation(Lib.koin)
    implementation(Lib.koin_android)
    implementation(Lib.coil_compose)
    implementation(Lib.room)
    kapt(Lib.room_compiler)
    implementation(Lib.room_ktx)

    implementation(TestLib.mockito_kotlin)
    testImplementation(TestLib.junit)
    testImplementation(TestLib.kotlin_test)
    testImplementation(TestLib.kotlin_coroutines_test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}