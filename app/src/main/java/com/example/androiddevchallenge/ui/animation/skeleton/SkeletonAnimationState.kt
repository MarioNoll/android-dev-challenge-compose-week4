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
package com.example.androiddevchallenge.ui.animation.skeleton

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.animation.Animatable as ColorAnimatable

class SkeletonAnimationState(
    val topCurveProgressAnimatable: Animatable<Float, AnimationVector1D>,
    val bottomCurveProgressAnimatable: Animatable<Float, AnimationVector1D>,
    val verticalOffsetFactorAnimatable: Animatable<Float, AnimationVector1D>,
    val forecastAlphaAnimatable: Animatable<Float, AnimationVector1D>,
    val curveIntersectionColorAnimatable: Animatable<Color, AnimationVector4D>,
    val bottomSurfaceColorAnimatable: Animatable<Color, AnimationVector4D>,
) {
    val topCurveProgress
        get() = topCurveProgressAnimatable.value

    val bottomCurveProgress
        get() = bottomCurveProgressAnimatable.value

    val verticalOffsetFactor
        get() = verticalOffsetFactorAnimatable.value

    val forecastAlpha
        get() = forecastAlphaAnimatable.value

    val curveIntersectionColor
        get() = curveIntersectionColorAnimatable.value

    val bottomSurfaceColor
        get() = bottomSurfaceColorAnimatable.value
}

@Composable
fun updateSkeletonAnimation(): SkeletonAnimationState {
    val secondaryVariant = MaterialTheme.colors.secondaryVariant
    val colorSurface = MaterialTheme.colors.surface

    val topCurveProgress = remember { Animatable(0F) }
    val bottomCurveProgress = remember { Animatable(0F) }
    val verticalOffsetFactor = remember { Animatable(1F) }
    val forecastAlpha = remember { Animatable(0F) }

    val curveIntersectionColor = remember {
        ColorAnimatable(
            secondaryVariant.copy(
                alpha = 0F
            )
        )
    }
    val bottomSurfaceColor = remember {
        ColorAnimatable(
            colorSurface.copy(
                alpha = 0F
            )
        )
    }

    LaunchedEffect(true) {
        launch {
            verticalOffsetFactor.animateTo(
                targetValue = 0F,
                animationSpec = tween(durationMillis = 700, delayMillis = 300)
            )
        }
        launch {
            topCurveProgress.animateTo(
                targetValue = 1F,
                animationSpec = tween(durationMillis = 700, delayMillis = 900)
            )
        }
        launch {
            bottomCurveProgress.animateTo(
                targetValue = 1F,
                animationSpec = tween(durationMillis = 700, delayMillis = 1100)
            )
        }
        launch {
            curveIntersectionColor.animateTo(
                targetValue = secondaryVariant,
                animationSpec = tween(durationMillis = 700, delayMillis = 1200)
            )
        }
        launch {
            bottomSurfaceColor.animateTo(
                targetValue = colorSurface,
                animationSpec = tween(durationMillis = 500, delayMillis = 1400)
            )

            forecastAlpha.animateTo(
                targetValue = 1F,
                animationSpec = tween(durationMillis = 400)
            )
        }
    }

    return SkeletonAnimationState(
        topCurveProgressAnimatable = topCurveProgress,
        bottomCurveProgressAnimatable = bottomCurveProgress,
        verticalOffsetFactorAnimatable = verticalOffsetFactor,
        forecastAlphaAnimatable = forecastAlpha,
        curveIntersectionColorAnimatable = curveIntersectionColor,
        bottomSurfaceColorAnimatable = bottomSurfaceColor
    )
}
