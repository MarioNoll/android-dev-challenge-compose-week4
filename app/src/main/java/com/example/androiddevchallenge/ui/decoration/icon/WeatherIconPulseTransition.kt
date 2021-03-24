package com.example.androiddevchallenge.ui.decoration.icon

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.androiddevchallenge.WeatherViewState

class WeatherIconTransitionData(
    weatherIconAlpha: State<Float>
) {
    val weatherIconAlpha by weatherIconAlpha
}

@Composable
fun updateWeatherIconPulseTransition(viewState: WeatherViewState): WeatherIconTransitionData {
    val transition = updateTransition(viewState)
    val infiniteTransition = rememberInfiniteTransition()
    val defaultAlpha = 0.1F

    val loadingAlpha = infiniteTransition.animateFloat(
        initialValue = defaultAlpha, targetValue = 0.2F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val isLoading = viewState is WeatherViewState.Search && viewState.inputViewState.isLoading
    val forecastAlpha = transition.animateFloat(transitionSpec = {
        tween()
    }) {
        when (viewState) {
            is WeatherViewState.Result -> 0.07F
            is WeatherViewState.Search -> {
                if (isLoading) {
                    loadingAlpha.value
                } else {
                    defaultAlpha
                }
            }
        }
    }

    return remember(viewState, transition, infiniteTransition) {
        WeatherIconTransitionData(
            weatherIconAlpha = if (isLoading) loadingAlpha else forecastAlpha
        )
    }
}
