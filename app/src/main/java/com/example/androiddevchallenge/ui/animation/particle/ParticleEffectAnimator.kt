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

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import kotlin.math.roundToInt

fun DrawScope.drawParticles(
    particleColor: Color,
    particles: List<Pair<Particle, ParticleEffectAnimationState>>,
    weatherFont: Typeface?
) {
    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = particleColor.toArgb()
        typeface = weatherFont
        colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    drawIntoCanvas { canvas ->
        particles.forEach { (particle, animationState) ->

            paint.apply {
                textSize = particle.size.toPx()
                alpha = (255 * particle.alpha).roundToInt()
            }

            val x = particleX(particle, animationState)
            val y = particleY(particle, animationState)

            val isVisible = when (particle.direction) {
                ParticleDirection.Horizontal -> x > 0
                ParticleDirection.Vertical -> y > 0
            }

            if (isVisible) {
                canvas.nativeCanvas.drawText(
                    particle.icon.value,
                    particleX(particle, animationState),
                    particleY(particle, animationState),
                    paint
                )
            }
        }
    }
}

private fun DrawScope.particleX(
    particle: Particle,
    animationState: ParticleEffectAnimationState
): Float = when (particle.direction) {
    ParticleDirection.Horizontal -> animationState.xFactor * size.width
    ParticleDirection.Vertical -> offset(particle, animationState) { xFactor }
}

private fun DrawScope.particleY(
    particle: Particle,
    animationState: ParticleEffectAnimationState
): Float = when (particle.direction) {
    ParticleDirection.Horizontal -> offset(particle, animationState) { yFactor }
    ParticleDirection.Vertical -> animationState.yFactor * size.height
}

private fun DrawScope.offset(
    particle: Particle,
    animationState: ParticleEffectAnimationState,
    withFactor: ParticleEffectAnimationState.() -> Float
): Float {
    val startOffset = particle.startOffsetFactor * when (particle.direction) {
        ParticleDirection.Horizontal -> size.height
        ParticleDirection.Vertical -> size.width
    }

    return startOffset + particle.offsetTarget.toPx() * withFactor(animationState)
}

private val colorMatrix = ColorMatrix(
    floatArrayOf(
        255f, 255f, 255f, 0f, 0f,
        255f, 255f, 255f, 0f, 0f,
        255f, 255f, 255f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
)
