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
