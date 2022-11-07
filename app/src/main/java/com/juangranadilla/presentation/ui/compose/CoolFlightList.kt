package com.juangranadilla.presentation.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juangranadilla.domain.model.Flight

@Preview
@Composable
fun coolFlightList(
    modifier: Modifier = Modifier,
    flights: List<Flight> = getPreviewFlightList()
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = flights) { flight ->
            coolFlightRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                flight = flight
            )
        }
    }
}

fun getPreviewFlightList() = listOf(
    getPreviewFlight(),
    getPreviewFlight(),
    getPreviewFlight(),
    getPreviewFlight(),
    getPreviewFlight()
)