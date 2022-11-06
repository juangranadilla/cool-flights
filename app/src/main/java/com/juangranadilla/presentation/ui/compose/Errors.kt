package com.juangranadilla.presentation.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.juangranadilla.coolflights.R
import kiwi.orbit.compose.illustrations.Illustrations
import kiwi.orbit.compose.ui.controls.EmptyState

@Preview
@Composable
fun ServerError() {
    EmptyState(
        illustration = Illustrations.Error404,
        title = stringResource(id = R.string.server_error_title),
        description = stringResource(id = R.string.server_error_description)
    )
}


@Preview
@Composable
fun NoFlightsError() {
    EmptyState(
        illustration = Illustrations.NoResults,
        title = stringResource(id = R.string.no_flights_title),
        description = stringResource(id = R.string.no_flights_description)
    )
}
