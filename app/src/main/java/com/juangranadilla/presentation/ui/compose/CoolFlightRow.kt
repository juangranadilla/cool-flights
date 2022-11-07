package com.juangranadilla.presentation.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import java.util.*

@Preview
@Composable
fun coolFlightRow(
    modifier: Modifier = Modifier,
    flight: Flight = getPreviewFlight()
) {
    SurfaceCard(modifier = modifier.height(165.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(flight.to.getImageUrl())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            placeholder = ColorPainter(OrbitTheme.colors.content.disabled),
            fallback = ColorPainter(OrbitTheme.colors.content.disabled)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SurfaceCard(backgroundColor = OrbitTheme.colors.primary.normal) {
                Text(
                    text = flight.price,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp),
                    color = Color.White,
                    style = OrbitTheme.typography.title4
                )
            }
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = flight.to.name,
                    color = Color.White,
                    style = OrbitTheme.typography.title2
                )
                Text(
                    text = flight.to.countryName,
                    color = Color.White,
                    style = OrbitTheme.typography.title4
                )
            }
        }
    }
}

fun getPreviewFlight() = Flight(
    id = "2",
    from = City("Madrid", "Spain", "ES"),
    to = City("Budapest", "Hungary", "HU"),
    price = "81,00 €",
    departure = Calendar.getInstance(),
    arrival = Calendar.getInstance(),
    duration = "3.5 h"
)