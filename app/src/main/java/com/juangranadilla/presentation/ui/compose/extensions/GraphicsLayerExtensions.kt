package com.juangranadilla.presentation.ui.compose.extensions

import androidx.compose.ui.graphics.GraphicsLayerScope
import com.juangranadilla.presentation.ui.compose.model.RotationAxis

fun GraphicsLayerScope.setRotationValues(rotationAxis: RotationAxis, rotationValue: Float) {
    when (rotationAxis) {
        RotationAxis.AxisX -> rotationX = rotationValue
        RotationAxis.AxisY -> rotationY = rotationValue
    }
}