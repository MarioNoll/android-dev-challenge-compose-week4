package com.example.androiddevchallenge

import com.example.androiddevchallenge.ui.forecast.ForecastViewState
import com.example.androiddevchallenge.ui.input.search.SearchInputViewState

sealed class WeatherViewState {
    abstract val inputViewState: SearchInputViewState

    data class Search(
        override val inputViewState: SearchInputViewState
    ) : WeatherViewState()

    data class Result(
        override val inputViewState: SearchInputViewState,
        val forecastViewState: ForecastViewState
    ) : WeatherViewState()

    companion object {
        val DEFAULT = Search(
            inputViewState = SearchInputViewState.DEFAULT
        )
    }
}
