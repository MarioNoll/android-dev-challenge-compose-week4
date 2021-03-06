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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF596A88),
    onPrimary = Color.White,
    primaryVariant = Color(0xFF5F6A83),
    secondary = Color(0xFF7487A8),
    secondaryVariant = Color(0xFF444C5E),
    background = Color(0xFF596A88),
    surface = Color(0xFF2C2C2C),
    onSurface = Color(0xFFEBF1FD)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFEA8324),
    onPrimary = Color.White,
    primaryVariant = Color(0xFFE57D16),
    secondary = Color(0xFF0F2942F),
    secondaryVariant = Color(0xFFFFC989),
    background = Color(0xFFEA8324),
    surface = Color.White,
    onSurface = Color(0xFF64411F),
    onBackground = Color.White
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
