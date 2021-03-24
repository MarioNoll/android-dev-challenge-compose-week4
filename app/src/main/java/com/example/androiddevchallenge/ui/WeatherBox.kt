package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.WeatherViewState
import com.example.androiddevchallenge.ui.decoration.BackgroundDecoration
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemListener
import com.example.androiddevchallenge.ui.input.FloatingInput
import com.example.androiddevchallenge.ui.input.search.SearchInputListener
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun WeatherBox(
    inputListener: SearchInputListener,
    forecastListItemListener: ForecastListItemListener,
    viewState: WeatherViewState
) {
    BackgroundDecoration(viewState = viewState)

    if (viewState is WeatherViewState.Result) {
        WeatherContent(
            listener = forecastListItemListener,
            viewState = viewState.forecastViewState
        )
    }

    FloatingInput(
        listener = inputListener,
        viewState = viewState
    )
}

@Preview(widthDp = 360, heightDp = 100)
@Composable
fun WeatherBoxPreview() {
    MyTheme {
        ProvideWindowInsets {
            WeatherBox(
                inputListener = SearchInputListener.NONE,
                forecastListItemListener = ForecastListItemListener.NONE,
                viewState = WeatherViewState.DEFAULT
            )
        }
    }
}