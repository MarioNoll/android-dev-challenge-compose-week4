package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.ui.icons.WeatherIcon
import java.time.LocalDate
import java.time.LocalTime

typealias Forecast = List<Pair<ForecastDay, List<ForecastHour>>>

data class ForecastDay(
    val ambient: Ambient,
    val date: LocalDate,
    val icon: WeatherIcon,
    val tempLowDegree: Int,
    val tempHighDegree: Int,
    val startHourForecastIndex: Int
) {
    init {
        require(startHourForecastIndex in 0..23)
    }
}

data class ForecastHour(
    val time: LocalTime,
    val icon: WeatherIcon,
    val tempDegree: Int,
    val feltTempDegree: Int,
    val precipitationProbability: Int,
    val description: String
)

enum class Ambient {
    Rain,
    Snow,
    Clouds,
    HeavyRain,
    Windy
}
