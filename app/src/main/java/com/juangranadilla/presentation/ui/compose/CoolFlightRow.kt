package com.juangranadilla.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.juangranadilla.domain.extensions.getDayAndMonthFormatted
import com.juangranadilla.domain.extensions.getHour
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.presentation.ui.compose.model.Face
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.BadgeInfoSubtle
import kiwi.orbit.compose.ui.controls.SurfaceCard
import kiwi.orbit.compose.ui.controls.Text
import java.util.*

@Preview
@Composable
fun coolFlightRow(
    modifier: Modifier = Modifier,
    flight: Flight = getPreviewFlight()
) {
    var face by remember {
        mutableStateOf(Face.Front)
    }

    SurfaceFlipCard(
        modifier = modifier.height(165.dp),
        face = face,
        onClick = { face = face.next },
        front = { coolFlightRowFront(flight) },
        back = { coolFlightRowBack(flight) }
    )
}

@Composable
fun coolFlightRowFront(flight: Flight = getPreviewFlight()) {
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

@Preview
@Composable
fun coolFlightRowBack(flight: Flight = getPreviewFlight()) {
    SurfaceCard(
        modifier = Modifier
            .height(165.dp)
            .fillMaxSize(),
        backgroundColor = OrbitTheme.colors.primary.normal
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            CityAndTimeColumn(
                modifier = Modifier
                    .weight(0.4f)
                    .align(Alignment.CenterVertically),
                cityName = flight.from.name,
                countryName = flight.from.countryName,
                date = flight.departure.getDayAndMonthFormatted(),
                time = flight.departure.getHour()
            )
            Column(
                Modifier
                    .weight(0.2f)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = Icons.FlightDirect,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                BadgeInfoSubtle(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = flight.duration,
                        color = OrbitTheme.colors.info.onSubtle,
                        style = OrbitTheme.typography.bodySmallBold
                    )
                }
            }
            CityAndTimeColumn(
                modifier = Modifier
                    .weight(0.4f)
                    .align(Alignment.CenterVertically),
                cityName = flight.to.name,
                countryName = flight.to.countryName,
                date = flight.arrival.getDayAndMonthFormatted(),
                time = flight.arrival.getHour()
            )
        }
    }
}

@Composable
fun CityAndTimeColumn(
    modifier: Modifier = Modifier,
    cityName: String,
    countryName: String,
    date: String,
    time: String
) {
    Column(modifier = modifier) {
        Text(
            text = cityName,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            style = OrbitTheme.typography.title3
        )
        Text(
            text = countryName,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            style = OrbitTheme.typography.bodyNormalBold
        )
        Text(
            text = date,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            color = Color.White,
            style = OrbitTheme.typography.bodyNormalMedium
        )
        Text(
            text = time,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            style = OrbitTheme.typography.bodySmallMedium
        )
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