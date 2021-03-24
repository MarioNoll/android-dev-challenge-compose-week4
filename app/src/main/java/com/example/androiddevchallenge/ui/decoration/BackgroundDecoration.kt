package com.example.androiddevchallenge.ui.decoration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.WeatherViewState
import com.example.androiddevchallenge.ui.decoration.icon.WeatherIconDecoration
import dev.chrisbanes.accompanist.insets.LocalWindowInsets

@Composable
fun BackgroundDecoration(viewState: WeatherViewState) {
    WeatherIconDecoration(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    0.5F to MaterialTheme.colors.secondary,
                    1.0F to MaterialTheme.colors.background
                )
            )
            .padding(bottom = LocalWindowInsets.current.navigationBars.bottom.dp),
        viewState = viewState
    )
}
