package com.example.androiddevchallenge.ui.input

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

class InputTransitionData(
    placeholderAlpha: State<Float>,
    textFieldAlpha: State<Float>
) {
    val placeholderAlpha by placeholderAlpha
    val textFieldAlpha by textFieldAlpha
}

@Composable
fun updateInputTransitionData(showCity: Boolean): InputTransitionData {
    val transition = updateTransition(showCity)

    val placeholderAlpha = transition.animateFloat(transitionSpec = { tween() }) {
        if (showCity) {
            0F
        } else {
            1F
        }
    }

    val textFieldAlpha = transition.animateFloat(transitionSpec = {
        tween(delayMillis = 150)
    }) {
        if (showCity) {
            1F
        } else {
            0F
        }
    }

    return remember(transition) {
        InputTransitionData(
            placeholderAlpha = placeholderAlpha,
            textFieldAlpha = textFieldAlpha
        )
    }
}
