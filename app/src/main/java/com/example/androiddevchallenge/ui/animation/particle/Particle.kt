package com.example.androiddevchallenge.ui.animation.particle

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.icons.WeatherIcon
import kotlin.random.Random

data class Particle(
    val icon: WeatherIcon,
    val direction: ParticleDirection,
    val size: Dp = 0.dp,
    val offsetTarget: Dp = 0.dp,
    val startOffsetFactor: Float = Random.nextFloat(),
    val alpha: Float = Random.nextFloat()
)

enum class ParticleDirection {
    Horizontal,
    Vertical
}