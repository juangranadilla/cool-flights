package com.juangranadilla.presentation.ui.compose.model

enum class Face(val angle: Float) {

    Front(0f) {
        override val next: Face
            get() = Back
    },

    Back(180f) {
        override val next: Face
            get() = Front
    };

    abstract val next: Face
}