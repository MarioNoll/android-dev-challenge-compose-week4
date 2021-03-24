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

    val textFieldAlpha = transition.animateFloat(
        transitionSpec = {
            tween(delayMillis = 150)
        }
    ) {
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
