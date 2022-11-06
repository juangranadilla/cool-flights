package com.juangranadilla.presentation.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import com.juangranadilla.presentation.ui.viewmodel.CoolFlightsViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.InlineLoading
import kiwi.orbit.compose.ui.controls.Surface
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar

@Composable
fun coolFlightsApp(viewModel: CoolFlightsViewModel = viewModel()) {

    val coolFlightsState by viewModel.coolFlightsLiveData.observeAsState()

    OrbitTheme(
        content = {
            Column {
                TopAppBar(title = { Text(text = "Cool Flights") })
                Surface(modifier = Modifier.fillMaxSize()) {
                    when (coolFlightsState) {
                        is DataState.Loading -> InlineLoading(modifier = Modifier.fillMaxSize())
                        is DataState.Error -> ServerError()
                        is DataState.Success -> {
                            val state = (coolFlightsState as DataState.Success<List<Flight>>)
                            state.value.takeIf { it.isNotEmpty() }?.let {
                                coolFlightList(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    state.value
                                )
                            } ?: NoFlightsError()
                        }
                        else -> ServerError()
                    }
                }
            }
        }
    )
}