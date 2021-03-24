package com.example.androiddevchallenge.ui.forecast

import com.example.androiddevchallenge.data.Ambient
import com.example.androiddevchallenge.data.ForecastDay
import com.example.androiddevchallenge.data.ForecastHour

data class ForecastViewState(
    val selectedDayIndex: Int,
    val selectedHourIndex: Int,
    val scrollToHour: Boolean,
    val city: String,
    val days: List<ForecastDay>,
    private val allHours: List<List<ForecastHour>>
) {
    init {
        require(selectedDayIndex in days.indices)
    }

    val ambient: Ambient
        get() = days[selectedDayIndex].ambient

    val hours
        get() = allHours[selectedDayIndex]
}
