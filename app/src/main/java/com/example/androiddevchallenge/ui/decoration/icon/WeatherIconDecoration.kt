package com.example.androiddevchallenge.ui.decoration.icon

import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.WeatherViewState
import com.example.androiddevchallenge.ui.icons.OWM_WEATHER_ICONS
import com.example.androiddevchallenge.ui.icons.WeatherIcon
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun WeatherIconDecoration(
    modifier: Modifier,
    viewState: WeatherViewState
) {
    val weatherFont = LocalContext.current.run {
        ResourcesCompat.getFont(this, R.font.weathericons_regular_webfont)
    }
    val iconColor = MaterialTheme.colors.onBackground.toArgb()
    val randomWeatherIcons = remember<MutableList<Pair<WeatherIcon, Offset>>> { mutableListOf() }
    val transition = updateWeatherIconPulseTransition(viewState)

    Box(
        modifier = modifier.drawBehind {
            val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                color = iconColor
                alpha = (transition.weatherIconAlpha * 255).roundToInt()
                textSize = 24.dp.toPx()
                typeface = weatherFont
            }

            val weatherIconBounds = Rect()
            OWM_WEATHER_ICONS.values.first().value.run {
                paint.getTextBounds(this, 0, length, weatherIconBounds)
            }

            val iconMargin = 24.dp.toPx()
            val halfIconMargin = (iconMargin / 2F).roundToInt()
            val iconBoxWidth = (weatherIconBounds.width() + iconMargin)
            val iconBoxHeight = (weatherIconBounds.height() + paint.descent() + iconMargin)

            val iconBoxCenterX = iconBoxWidth / 2F
            val iconBoxCenterY = iconBoxHeight / 2F

            val columns = (size.width / iconBoxWidth).toInt()
            val rows = (size.height / iconBoxHeight).toInt()

            val baseLineToCenter = (paint.descent() + paint.ascent()) / 2F

            val shiftX = (size.width - columns * iconBoxWidth) / 2F
            val shiftY = (size.height - rows * iconBoxHeight) / 2F

            if (randomWeatherIcons.isEmpty()) {
                for (i in 0 until rows) {
                    for (j in 0 until columns) {

                        val randomX = Random.nextInt(from = -halfIconMargin, until = halfIconMargin)
                        val randomY = Random.nextInt(from = -halfIconMargin, until = halfIconMargin)

                        randomWeatherIcons.add(
                            OWM_WEATHER_ICONS.values.random() to Offset(
                                x = randomX + shiftX + iconBoxCenterX - weatherIconBounds.width() / 2F + iconBoxWidth * j,
                                y = randomY + shiftY + iconBoxCenterY - baseLineToCenter + iconBoxHeight * i,
                            )
                        )
                    }
                }
            }

            drawIntoCanvas {
                randomWeatherIcons.forEach { (weatherIcon, offset) ->
                    it.nativeCanvas.drawText(weatherIcon.value, offset.x, offset.y, paint)
                }
            }
        }
    )
}
