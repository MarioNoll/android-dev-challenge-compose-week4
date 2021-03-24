package com.example.androiddevchallenge.ui.forecast.lists.hourly

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.ForecastHour
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemColumn
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemListener
import com.example.androiddevchallenge.ui.forecast.lists.SelectedForecastItemTitle
import com.example.androiddevchallenge.ui.format.formatTime
import com.example.androiddevchallenge.ui.icons.OWM_WEATHER_ICONS
import com.example.androiddevchallenge.ui.icons.RAINDROP
import com.example.androiddevchallenge.ui.icons.WeatherIconText
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import kotlinx.coroutines.launch
import java.time.LocalTime
import kotlin.random.Random

@Composable
fun ForecastHoursList(
    listener: ForecastListItemListener,
    selectedHourIndex: Int,
    scrollToHour: Boolean,
    hours: List<ForecastHour>
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
    ) {
        itemsIndexed(hours) { index, hour ->
            ForecastHour(
                listener = listener,
                index = index,
                isSelected = selectedHourIndex == index,
                viewState = hour
            )
        }
    }

    if (scrollToHour) {
        coroutineScope.launch {
            listState.scrollToItem(index = selectedHourIndex)
        }
    }
}

@Composable
private fun ForecastHour(
    listener: ForecastListItemListener,
    index: Int,
    isSelected: Boolean,
    viewState: ForecastHour
) {
    ForecastListItemColumn(
        onClickLabel = stringResource(
            id = R.string.forecast_item_click_label,
            formatTime(time = viewState.time)
        ),
        onClick = { listener.onHourSelected(index) }
    ) {
        SelectedForecastItemTitle(
            isSelected = isSelected,
            text = formatTime(time = viewState.time),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.alpha(0.7F),
            text = stringResource(id = R.string.forecast_temp, viewState.tempDegree),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))


        WeatherIconText(
            icon = viewState.icon,
            fontSize = 24.sp,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.alpha(0.7F),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WeatherIconText(
                icon = RAINDROP,
                fontSize = 10.sp,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(
                    id = R.string.forecast_precipitation_probability,
                    viewState.precipitationProbability
                ),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 100)
@Composable
fun ForecastHoursListPreview() {
    MyTheme {
        ProvideWindowInsets {
            ForecastHoursList(
                listener = ForecastListItemListener.NONE,
                selectedHourIndex = 1,
                scrollToHour = true,
                hours = List(23) {
                    ForecastHour(
                        time = LocalTime.now(),
                        icon = OWM_WEATHER_ICONS.values.random(),
                        tempDegree = Random.nextInt(),
                        feltTempDegree = Random.nextInt(),
                        precipitationProbability = Random.nextInt(),
                        description = "Rainy"
                    )
                }
            )
        }
    }
}