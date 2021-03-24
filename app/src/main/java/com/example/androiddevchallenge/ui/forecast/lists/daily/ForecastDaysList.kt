package com.example.androiddevchallenge.ui.forecast.lists.daily

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Ambient
import com.example.androiddevchallenge.data.ForecastDay
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemColumn
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemListener
import com.example.androiddevchallenge.ui.forecast.lists.SelectedForecastItemTitle
import com.example.androiddevchallenge.ui.format.formatDate
import com.example.androiddevchallenge.ui.format.formatWeekDay
import com.example.androiddevchallenge.ui.icons.OWM_WEATHER_ICONS
import com.example.androiddevchallenge.ui.icons.WeatherIconText
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import java.time.LocalDate
import kotlin.random.Random

@Composable
fun ForecastDaysList(
    listener: ForecastListItemListener,
    selectedDayIndex: Int,
    days: List<ForecastDay>
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
    ) {
        itemsIndexed(days) { index, day ->
            ForecastDay(
                listener = listener,
                index = index,
                state = day,
                isSelected = index == selectedDayIndex
            )
        }
    }
}

@Composable
private fun ForecastDay(
    listener: ForecastListItemListener,
    index: Int,
    isSelected: Boolean,
    state: ForecastDay
) {
    ForecastListItemColumn(
        onClickLabel = stringResource(
            id = R.string.forecast_item_click_label,
            formatDate(day = state.date)
        ),
        onClick = { listener.onDaySelected(index) }
    ) {
        SelectedForecastItemTitle(
            isSelected = isSelected,
            text = formatWeekDay(date = state.date)
        )

        Spacer(modifier = Modifier.height(12.dp))

        WeatherIconText(
            icon = state.icon,
            fontSize = 24.sp,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.alpha(0.7F),
            text = stringResource(
                id = R.string.forecast_temp_range,
                state.tempLowDegree,
                state.tempHighDegree
            ),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(widthDp = 360, heightDp = 100)
@Composable
fun ForecastDaysListPreview() {
    MyTheme {
        ProvideWindowInsets {
            ForecastDaysList(
                listener = ForecastListItemListener.NONE,
                selectedDayIndex = 1,
                days = List(6) {
                    ForecastDay(
                        ambient = Ambient.values().random(),
                        date = LocalDate.now(),
                        icon = OWM_WEATHER_ICONS.values.random(),
                        tempLowDegree = Random.nextInt(),
                        tempHighDegree = Random.nextInt(),
                        startHourForecastIndex = 5
                    )
                }
            )
        }
    }
}