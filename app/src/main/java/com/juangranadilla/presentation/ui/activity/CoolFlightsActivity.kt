package com.juangranadilla.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import kiwi.orbit.compose.ui.OrbitTheme

class CoolFlightsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrbitTheme(
                content = {
                    coolFlights()
                }
            )
        }
    }

    @Composable
    fun coolFlights() {

    }
}