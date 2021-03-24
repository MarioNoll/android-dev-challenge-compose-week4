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
package com.example.androiddevchallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.WeatherRepository
import com.example.androiddevchallenge.di.ServiceLocator
import com.example.androiddevchallenge.ui.forecast.ForecastViewState
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemListener
import com.example.androiddevchallenge.ui.input.search.SearchInputListener
import com.example.androiddevchallenge.ui.input.search.SearchInputViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class WeatherViewModel(
    private val weatherRepo: WeatherRepository = ServiceLocator.weatherRepository
) : ViewModel(), SearchInputListener, ForecastListItemListener {

    private val _viewState = MutableStateFlow<WeatherViewState>(WeatherViewState.DEFAULT)
    private val inputStateFlow = MutableStateFlow(SearchInputViewState.DEFAULT)
    private val forecastStateFlow = MutableStateFlow<ForecastViewState?>(null)

    private var loadingJob: Job? = null

    val viewState: StateFlow<WeatherViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            combine(inputStateFlow, forecastStateFlow) { inputViewState, forecast ->
                if (forecast == null) {
                    WeatherViewState.Search(inputViewState = inputViewState)
                } else {
                    WeatherViewState.Result(
                        inputViewState = inputViewState,
                        forecastViewState = forecast
                    )
                }
            }
                .collect {
                    _viewState.value = it
                }
        }
    }

    override fun onInputClicked() {
        updateInputState {
            copy(
                showCity = true,
                isFocused = true
            )
        }
    }

    override fun onInputChanged(input: String) {
        loadingJob?.cancel()
        forecastStateFlow.value = null
        updateInputState {
            copy(
                city = input,
                isLoading = false
            )
        }
    }

    override fun onInputFocusLost() {
        updateInputState {
            copy(
                showCity = city.isNotBlank(),
                isFocused = false
            )
        }
    }

    override fun onSearchClicked() {
        val city = inputStateFlow.value.city
        val currentCity = forecastStateFlow.value?.city
        if (city.isNotBlank() && city != currentCity) {
            updateInputState {
                copy(
                    isFocused = false,
                    isLoading = true
                )
            }

            loadingJob?.cancel()
            loadingJob = viewModelScope.launch {
                val forecast = weatherRepo.weather(
                    now = LocalDateTime.now(),
                    city = city
                )

                val days = forecast.map { (day, _) -> day }
                forecastStateFlow.value = ForecastViewState(
                    selectedDayIndex = 0,
                    selectedHourIndex = days.first().startHourForecastIndex,
                    scrollToHour = true,
                    days = days,
                    city = city,
                    allHours = forecast.map { (_, hours) -> hours }
                )

                updateInputState {
                    copy(
                        isLoading = false,
                        isFocused = false
                    )
                }
            }
        }
    }

    override fun onDaySelected(index: Int) {
        updateForecastState {
            copy(
                scrollToHour = true,
                selectedDayIndex = index,
                selectedHourIndex = 0
            )
        }
    }

    override fun onHourSelected(index: Int) {
        updateForecastState {
            copy(
                scrollToHour = false,
                selectedHourIndex = index
            )
        }
    }

    private fun updateInputState(update: SearchInputViewState.() -> SearchInputViewState) {
        inputStateFlow.value = inputStateFlow.value.update()
    }

    private fun updateForecastState(update: ForecastViewState.() -> ForecastViewState) {
        forecastStateFlow.value = forecastStateFlow.value?.update()
    }
}
