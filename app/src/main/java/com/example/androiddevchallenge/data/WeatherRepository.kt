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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.ui.icons.CLOUDS_DAY
import com.example.androiddevchallenge.ui.icons.CLOUDS_NEUTRAL
import com.example.androiddevchallenge.ui.icons.CLOUDS_NIGHT
import com.example.androiddevchallenge.ui.icons.HEAVY_RAIN_DAY
import com.example.androiddevchallenge.ui.icons.HEAVY_RAIN_NEUTRAL
import com.example.androiddevchallenge.ui.icons.HEAVY_RAIN_NIGHT
import com.example.androiddevchallenge.ui.icons.RAIN_DAY
import com.example.androiddevchallenge.ui.icons.RAIN_NEUTRAL
import com.example.androiddevchallenge.ui.icons.RAIN_NIGHT
import com.example.androiddevchallenge.ui.icons.SNOW_DAY
import com.example.androiddevchallenge.ui.icons.SNOW_NEUTRAL
import com.example.androiddevchallenge.ui.icons.SNOW_NIGHT
import com.example.androiddevchallenge.ui.icons.WIND_DAY
import com.example.androiddevchallenge.ui.icons.WIND_NEUTRAL
import com.example.androiddevchallenge.ui.icons.WIND_NIGHT
import com.example.androiddevchallenge.ui.icons.WeatherIcon
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.random.Random

class WeatherRepository {

    suspend fun weather(
        now: LocalDateTime,
        @Suppress("UNUSED_PARAMETER") city: String
    ): Forecast {
        // Simulate network call
        delay(1500)

        // TODO: Use an actual api instead of dummy data.
        return listOf(
            Ambient.Rain,
            Ambient.Clouds,
            Ambient.Snow,
            Ambient.HeavyRain,
            Ambient.Windy,
            Ambient.Rain,
            Ambient.Snow
        ).mapIndexed { index, weatherAmbient ->
            val dateTime = if (index == 0) {
                now
            } else {
                now.toLocalDate()
                    .plusDays(index.toLong())
                    .atStartOfDay()
            }

            weatherForAmbient(
                ambient = weatherAmbient,
                today = now.toLocalDate(),
                dateTime = dateTime,
            )
        }
    }

    private fun weatherForAmbient(
        ambient: Ambient,
        today: LocalDate,
        dateTime: LocalDateTime
    ): Pair<ForecastDay, List<ForecastHour>> {
        val hourlyForecast = MutableList(24) {
            val time = LocalTime.of(it, 0)

            val icon = when {
                time > LocalTime.of(20, 0) -> ambient.icon
                time > LocalTime.of(10, 0) -> ambient.iconDay
                time > LocalTime.of(6, 0) -> ambient.icon
                else -> ambient.iconNight
            }

            ForecastHour(
                time = LocalTime.of(it, 0),
                icon = icon,
                tempDegree = randomTemperature(ambient),
                feltTempDegree = randomTemperature(ambient),
                precipitationProbability = ambient.precipitationProbability,
                description = ambient.description
            )
        }

        val selectedHourlyForecastIndex = if (today == dateTime.toLocalDate()) {
            dateTime.hour
        } else {
            0
        }

        return ForecastDay(
            ambient = ambient,
            date = dateTime.toLocalDate(),
            icon = ambient.icon,
            tempLowDegree = ambient.tempLow,
            tempHighDegree = ambient.tempHigh,
            startHourForecastIndex = selectedHourlyForecastIndex
        ) to hourlyForecast
    }

    private fun randomTemperature(ambient: Ambient): Int =
        Random.nextInt(from = ambient.tempLow, until = ambient.tempHigh + 1)
}

private val Ambient.icon: WeatherIcon
    get() = when (this) {
        Ambient.Rain -> RAIN_NEUTRAL
        Ambient.Snow -> SNOW_NEUTRAL
        Ambient.Clouds -> CLOUDS_NEUTRAL
        Ambient.Windy -> WIND_NEUTRAL
        Ambient.HeavyRain -> HEAVY_RAIN_NEUTRAL
    }

private val Ambient.iconDay: WeatherIcon
    get() = when (this) {
        Ambient.Rain -> RAIN_DAY
        Ambient.Snow -> SNOW_DAY
        Ambient.Clouds -> CLOUDS_DAY
        Ambient.Windy -> WIND_DAY
        Ambient.HeavyRain -> HEAVY_RAIN_DAY
    }

private val Ambient.iconNight: WeatherIcon
    get() = when (this) {
        Ambient.Rain -> RAIN_NIGHT
        Ambient.Snow -> SNOW_NIGHT
        Ambient.Clouds -> CLOUDS_NIGHT
        Ambient.Windy -> WIND_NIGHT
        Ambient.HeavyRain -> HEAVY_RAIN_NIGHT
    }

private val Ambient.tempLow: Int
    get() = when (this) {
        Ambient.Rain -> 5
        Ambient.Snow -> -19
        Ambient.Clouds -> 15
        Ambient.Windy -> 10
        Ambient.HeavyRain -> 20
    }

private val Ambient.tempHigh: Int
    get() = when (this) {
        Ambient.Rain -> 15
        Ambient.Snow -> -5
        Ambient.Clouds -> 19
        Ambient.Windy -> 16
        Ambient.HeavyRain -> 28
    }

private val Ambient.precipitationProbability: Int
    get() = when (this) {
        Ambient.Rain -> Random.nextInt(40, 61)
        Ambient.Snow -> Random.nextInt(80, 98)
        Ambient.Clouds -> Random.nextInt(10, 15)
        Ambient.Windy -> Random.nextInt(0, 5)
        Ambient.HeavyRain -> Random.nextInt(80, 94)
    }

private val Ambient.description: String
    get() = when (this) {
        Ambient.Rain -> "Moderate rain. Light breeze."
        Ambient.Snow -> "Snow. Light breeze."
        Ambient.Clouds -> "Overcast clouds. Light breeze."
        Ambient.Windy -> "Clear sky. Heavy breeze."
        Ambient.HeavyRain -> "Heavy rain. Mild breeze."
    }
