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

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.format.formatDateTime
import com.example.androiddevchallenge.ui.icons.WeatherIconText

@Composable
fun ForecastTeaser(
    modifier: Modifier,
    viewState: ForecastViewState
) {
    Crossfade(
        modifier = Modifier,
        targetState = viewState.selectedDayIndex to viewState.selectedHourIndex,
        animationSpec = tween(durationMillis = 600)
    ) { (selectedDayIndex, selectedHourIndex) ->

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val day = viewState.days[selectedDayIndex]
            val hour = viewState.hours[selectedHourIndex]

            Text(
                text = formatDateTime(day = day.date, time = hour.time),
                modifier = Modifier.paddingFromBaseline(top = 28.dp),
                style = MaterialTheme.typography.subtitle1
            )

            WeatherIconText(
                modifier = Modifier.paddingFromBaseline(top = 88.dp, bottom = 34.dp),
                icon = hour.icon,
                fontSize = 68.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.alpha(0.5F),
                    text = stringResource(id = R.string.forecast_temp_low, day.tempLowDegree),
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = stringResource(id = R.string.forecast_temp, hour.tempDegree),
                    style = MaterialTheme.typography.h2
                )

                Text(
                    modifier = Modifier.alpha(0.5F),
                    text = stringResource(id = R.string.forecast_temp_high, day.tempHighDegree),
                    style = MaterialTheme.typography.h5
                )
            }

            Text(
                modifier = Modifier.paddingFromBaseline(top = 42.dp, bottom = 16.dp),
                text = stringResource(id = R.string.forecast_temp_felt, hour.feltTempDegree),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
            )

            Text(
                modifier = Modifier.paddingFromBaseline(bottom = 28.dp),
                text = hour.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
