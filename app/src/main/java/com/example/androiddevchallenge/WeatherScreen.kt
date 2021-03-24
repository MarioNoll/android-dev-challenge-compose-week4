package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.WeatherBox

@Composable
fun WeatherScreen() {
    val viewModel = viewModel(WeatherViewModel::class.java)
    val viewState by viewModel.viewState.collectAsState()

    WeatherBox(
        inputListener = viewModel,
        forecastListItemListener = viewModel,
        viewState = viewState
    )
}
