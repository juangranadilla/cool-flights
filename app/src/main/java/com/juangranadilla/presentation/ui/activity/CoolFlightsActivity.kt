package com.juangranadilla.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.juangranadilla.presentation.ui.compose.coolFlightsApp
import com.juangranadilla.presentation.ui.viewmodel.CoolFlightsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoolFlightsActivity : ComponentActivity() {

    private val viewModel by viewModel<CoolFlightsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            coolFlightsApp(viewModel)
        }
    }
}