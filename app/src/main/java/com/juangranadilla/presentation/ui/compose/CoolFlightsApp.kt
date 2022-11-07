package com.juangranadilla.presentation.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juangranadilla.coolflights.R
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import com.juangranadilla.presentation.ui.viewmodel.CoolFlightsViewModel
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.*

@Composable
fun coolFlightsApp(viewModel: CoolFlightsViewModel = viewModel()) {
    val coolFlightsState by viewModel.coolFlightsLiveData.observeAsState()
    coolFlightsContent(coolFlightsState)
}

@Preview
@Composable
fun coolFlightsContent(
    state: DataState<List<Flight>>? = DataState.Success(getPreviewFlightList())
) {
    OrbitTheme(
        content = {
            Column {
                TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
                Surface(modifier = Modifier.fillMaxSize()) {
                    when (state) {
                        is DataState.Loading -> LinearIndeterminateProgressIndicator(
                            modifier = Modifier.wrapContentSize(Alignment.TopCenter)
                        )
                        is DataState.Error -> ServerError()
                        is DataState.Success -> {
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

@Preview
@Composable
fun coolFlightsLoading() {
    coolFlightsContent(state = DataState.Loading(true))
}

@Preview
@Composable
fun coolFlightsEmpty() {
    coolFlightsContent(state = DataState.Success(emptyList()))
}

@Preview
@Composable
fun coolFlightsError() {
    coolFlightsContent(state = DataState.Error(""))
}