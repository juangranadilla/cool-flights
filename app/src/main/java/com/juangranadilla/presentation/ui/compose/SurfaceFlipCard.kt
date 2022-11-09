package com.juangranadilla.presentation.ui.compose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.juangranadilla.presentation.ui.compose.extensions.setRotationValues
import com.juangranadilla.presentation.ui.compose.model.Face
import com.juangranadilla.presentation.ui.compose.model.RotationAxis
import kiwi.orbit.compose.ui.controls.SurfaceCard

@Composable
fun SurfaceFlipCard(
    modifier: Modifier = Modifier,
    face: Face,
    axis: RotationAxis = RotationAxis.AxisY,
    onClick: (Face) -> Unit,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = face.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    )

    SurfaceCard(
        onClick = { onClick(face) },
        modifier = modifier.graphicsLayer {
            setRotationValues(axis, rotation.value)
            cameraDistance = 12f * density
        }
    ) {
        if (rotation.value <= 90f) {
            Box(Modifier.fillMaxSize()) {
                front()
            }
        } else {
            Box(Modifier.fillMaxSize().graphicsLayer { setRotationValues(axis, 180f) }) {
                back()
            }
        }
    }
}