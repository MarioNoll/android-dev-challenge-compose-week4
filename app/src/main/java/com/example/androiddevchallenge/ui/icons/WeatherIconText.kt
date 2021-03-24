package com.example.androiddevchallenge.ui.icons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.example.androiddevchallenge.ui.theme.WeatherIconsFont

@Composable
fun WeatherIconText(
    modifier: Modifier = Modifier,
    icon: WeatherIcon,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified
) {
    Text(
        modifier = modifier,
        text = icon.value,
        fontFamily = WeatherIconsFont,
        fontSize = fontSize,
        color = color
    )
}