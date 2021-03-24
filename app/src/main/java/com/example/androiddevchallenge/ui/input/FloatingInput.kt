package com.example.androiddevchallenge.ui.input

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.WeatherViewState
import com.example.androiddevchallenge.ui.input.search.CitySearchInput
import com.example.androiddevchallenge.ui.input.search.SearchInputListener
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun FloatingInput(
    listener: SearchInputListener,
    viewState: WeatherViewState
) {
    val inputMarginTop = LocalDensity.current.run {
        LocalWindowInsets.current.statusBars.top.toDp() + 16.dp
    }

    val offsetFactor by updateFloatingInputOffsetFactor(viewState)

    BoxWithConstraints {
        val maxHeight = maxHeight
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CitySearchInput(
                listener = listener,
                viewState = viewState.inputViewState,
                modifier = Modifier
                    .padding(horizontal = 64.dp)
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)

                        layout(placeable.width, placeable.height) {
                            val center = (maxHeight - placeable.height.toDp()) / 2
                            val offsetRange = (center - inputMarginTop)

                            val y = (offsetRange * offsetFactor)
                                .plus(inputMarginTop)
                                .roundToPx()

                            placeable.place(x = 0, y = y)
                        }
                    })
        }
    }
}

@Composable
private fun updateFloatingInputOffsetFactor(viewState: WeatherViewState): State<Float> {
    val transition = updateTransition(viewState)

    val offsetFactor = transition.animateFloat(transitionSpec = {
        tween(durationMillis = 1000)
    }) {
        when (viewState) {
            is WeatherViewState.Result -> 0F
            is WeatherViewState.Search -> 1F
        }
    }

    return remember(transition) {
        offsetFactor
    }
}

@Preview(widthDp = 360, heightDp = 100)
@Composable
fun FloatingInputPreview() {
    MyTheme {
        ProvideWindowInsets {
            FloatingInput(
                listener = SearchInputListener.NONE,
                viewState = WeatherViewState.DEFAULT
            )
        }
    }
}