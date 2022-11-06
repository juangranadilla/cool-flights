package com.juangranadilla.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.juangranadilla.domain.model.City
import com.juangranadilla.domain.model.Flight
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.*
import java.util.*

class CoolFlightsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            coolFlightsApp()
        }
    }

    @Preview
    @Composable
    fun coolFlightsApp() {
        OrbitTheme(
            content = {
                Column {
                    TopAppBar(title = { Text(text = "Cool Flights") })
                    Surface(modifier = Modifier.fillMaxSize()) {
                        coolFlightList(
                            modifier = Modifier.padding(vertical = 4.dp),
                            flights = getMockFlights()
                        )
                    }
                }
            }
        )
    }

    @Composable
    fun coolFlightList(
        modifier: Modifier = Modifier,
        flights: List<Flight> = emptyList()
    ) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
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

    @Composable
    fun coolFlightRow(
        modifier: Modifier = Modifier,
        flight: Flight
    ) {
        SurfaceCard(modifier = modifier.height(200.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(flight.to.getImageUrl())
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                SurfaceCard(backgroundColor = OrbitTheme.colors.primary.normal) {
                    Text(
                        text = flight.price,
                        modifier = Modifier.align(Alignment.TopStart).padding(6.dp),
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

    private fun getMockFlights() = listOf(
        Flight(
            id = "1",
            from = City("Madrid", "Spain", "ES"),
            to = City("London", "United Kingdom", "GB"),
            price = "81,00 €",
            departure = Calendar.getInstance(),
            arrival = Calendar.getInstance(),
            duration = "3.5 h"
        ),
        Flight(
            id = "2",
            from = City("Madrid", "Spain", "ES"),
            to = City("Budapest", "Hungary", "HU"),
            price = "81,00 €",
            departure = Calendar.getInstance(),
            arrival = Calendar.getInstance(),
            duration = "3.5 h"
        ),
        Flight(
            id = "3",
            from = City("Madrid", "Spain", "ES"),
            to = City("Paris", "France", "FR"),
            price = "81,00 €",
            departure = Calendar.getInstance(),
            arrival = Calendar.getInstance(),
            duration = "3.5 h"
        ),
        Flight(
            id = "4",
            from = City("Madrid", "Spain", "ES"),
            to = City("Dublin", "Ireland", "IE"),
            price = "81,00 €",
            departure = Calendar.getInstance(),
            arrival = Calendar.getInstance(),
            duration = "3.5 h"
        ),
        Flight(
            id = "5",
            from = City("Málaga", "Spain", "ES"),
            to = City("Amsterdam", "Netherlands", "NL"),
            price = "81,00 €",
            departure = Calendar.getInstance(),
            arrival = Calendar.getInstance(),
            duration = "3.5 h"
        )
    )
}