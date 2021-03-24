/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.animation.particle

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Ambient
import com.example.androiddevchallenge.ui.forecast.ForecastViewState
import com.example.androiddevchallenge.ui.icons.CLOUD
import com.example.androiddevchallenge.ui.icons.CLOUDY
import com.example.androiddevchallenge.ui.icons.LEAF
import com.example.androiddevchallenge.ui.icons.RAINDROP
import com.example.androiddevchallenge.ui.icons.SNOWFLAKE
import com.example.androiddevchallenge.ui.icons.WeatherIcon
import kotlin.random.Random

class ParticleEffectAnimationState(
    val xFactorAnimatable: Animatable<Float, AnimationVector1D>,
    val yFactorAnimatable: Animatable<Float, AnimationVector1D>
) {
    val xFactor
        get() = xFactorAnimatable.value

    val yFactor
        get() = yFactorAnimatable.value
}

@Composable
fun updateParticleEffect(viewState: ForecastViewState): List<Pair<Particle, ParticleEffectAnimationState>> {
    val ambient = viewState.ambient

    val particles = remember(viewState.selectedDayIndex) {
        List(ambient.particleCount) {
            Particle(
                icon = ambient.particle,
                direction = ambient.particleDirection,
                size = ambient.particleSize,
                offsetTarget = ambient.targetOffset
            ) to ParticleEffectAnimationState(
                xFactorAnimatable = Animatable(-0.1F),
                yFactorAnimatable = Animatable(-0.1F)
            )
        }
    }

    return particles.onEach { (_, animState) ->
        val delay = Random.nextInt(from = 1000, until = 3000)
        val duration = Random.nextInt(from = 1000, until = 5000)

        LaunchedEffect(viewState.selectedDayIndex) {
            animState.yFactorAnimatable.animateTo(
                1F,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        delayMillis = delay,
                        easing = ambient.easing
                    )
                )
            )
        }

        LaunchedEffect(viewState.selectedDayIndex) {
            animState.xFactorAnimatable.animateTo(
                1F,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        delayMillis = delay,
                        easing = ambient.easing
                    )
                )
            )
        }
    }
}

private val Ambient.easing: Easing
    get() = when (this) {
        Ambient.Rain,
        Ambient.HeavyRain,
        Ambient.Snow,
        Ambient.Windy -> listOf(
            FastOutSlowInEasing,
            LinearOutSlowInEasing,
            FastOutLinearInEasing
        ).random()
        Ambient.Clouds -> LinearEasing
    }

private val Ambient.particle: WeatherIcon
    get() = when (this) {
        Ambient.Rain,
        Ambient.HeavyRain -> RAINDROP
        Ambient.Snow -> SNOWFLAKE
        Ambient.Clouds -> listOf(CLOUD, CLOUDY).random()
        Ambient.Windy -> LEAF
    }

private val Ambient.particleDirection: ParticleDirection
    get() = when (this) {
        Ambient.Rain,
        Ambient.HeavyRain,
        Ambient.Snow -> ParticleDirection.Vertical
        Ambient.Windy,
        Ambient.Clouds -> ParticleDirection.Horizontal
    }

private val Ambient.particleSize: Dp
    get() = when (this) {
        Ambient.Rain -> Random.nextInt(15, 20).dp
        Ambient.HeavyRain -> Random.nextInt(15, 25).dp
        Ambient.Snow -> Random.nextInt(12, 18).dp
        Ambient.Windy -> Random.nextInt(12, 16).dp
        Ambient.Clouds -> Random.nextInt(18, 25).dp
    }

private val Ambient.targetOffset: Dp
    get() = when (this) {
        Ambient.Rain -> Random.nextInt(4, 40).dp
        Ambient.HeavyRain -> Random.nextInt(30, 80).dp
        Ambient.Snow -> Random.nextInt(10, 16).dp
        Ambient.Windy -> Random.nextInt(80, 150).dp
        Ambient.Clouds -> Random.nextInt(0, 11).dp
    }

private val Ambient.particleCount: Int
    get() = when (this) {
        Ambient.Windy -> 25
        Ambient.Clouds -> 12
        Ambient.Rain -> 30
        Ambient.HeavyRain,
        Ambient.Snow -> 60
    }
