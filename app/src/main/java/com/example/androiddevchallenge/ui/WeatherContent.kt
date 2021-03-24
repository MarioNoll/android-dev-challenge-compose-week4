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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.animation.particle.drawParticles
import com.example.androiddevchallenge.ui.animation.particle.updateParticleEffect
import com.example.androiddevchallenge.ui.animation.skeleton.updateSkeletonAnimation
import com.example.androiddevchallenge.ui.decoration.drawWave
import com.example.androiddevchallenge.ui.forecast.ForecastTeaser
import com.example.androiddevchallenge.ui.forecast.ForecastViewState
import com.example.androiddevchallenge.ui.forecast.lists.ForecastListItemListener
import com.example.androiddevchallenge.ui.forecast.lists.daily.ForecastDaysList
import com.example.androiddevchallenge.ui.forecast.lists.hourly.ForecastHoursList
import dev.chrisbanes.accompanist.insets.LocalWindowInsets

@Composable
fun WeatherContent(
    modifier: Modifier = Modifier,
    listener: ForecastListItemListener,
    viewState: ForecastViewState
) {
    val navBarPadding = LocalDensity.current.run {
        LocalWindowInsets.current.navigationBars.bottom.toDp()
    }

    val statusBarPadding = LocalDensity.current.run {
        LocalWindowInsets.current.statusBars.top.toDp()
    }

    val waveHeight = 80.dp
    val primaryVariant = MaterialTheme.colors.primaryVariant
    val onBackgroundColor = MaterialTheme.colors.onBackground
    val weatherFont =
        ResourcesCompat.getFont(LocalContext.current, R.font.weathericons_regular_webfont)

    val particles = updateParticleEffect(viewState)
    val skeleton = updateSkeletonAnimation()

    CompositionLocalProvider(LocalContentColor.provides(onBackgroundColor)) {
        BoxWithConstraints {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .heightIn(min = maxHeight)
                    .offset(y = maxHeight * skeleton.verticalOffsetFactor)
                    .fillMaxWidth()
            ) {

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawParticles(
                                particleColor = onBackgroundColor,
                                particles = particles,
                                weatherFont = weatherFont
                            )
                        }
                        .padding(top = statusBarPadding + 72.dp, bottom = navBarPadding)
                        .drawBehind {
                            drawWave(
                                waveHeight = waveHeight,
                                strokeColor = primaryVariant,
                                topCurveProgress = skeleton.topCurveProgress,
                                bottomCurveProgress = skeleton.bottomCurveProgress,
                                curveIntersectionColor = skeleton.curveIntersectionColor,
                                bottomSurfaceColor = skeleton.bottomSurfaceColor
                            )
                        }
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)

                            layout(placeable.width, placeable.height + waveHeight.roundToPx()) {
                                placeable.place(x = 0, y = 0)
                            }
                        }
                ) {
                    ForecastTeaser(
                        modifier = Modifier.fillMaxWidth(),
                        viewState = viewState
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = skeleton.bottomSurfaceColor)
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.alpha(skeleton.forecastAlpha)
                    ) {
                        ForecastDaysList(
                            selectedDayIndex = viewState.selectedDayIndex,
                            days = viewState.days,
                            listener = listener
                        )

                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Divider(modifier = Modifier.weight(1F))

                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = stringResource(id = R.string.forecast_divider_title),
                                style = MaterialTheme.typography.subtitle2,
                                color = MaterialTheme.colors.onSurface
                            )

                            Divider(modifier = Modifier.weight(1F))
                        }

                        ForecastHoursList(
                            listener = listener,
                            selectedHourIndex = viewState.selectedHourIndex,
                            scrollToHour = viewState.scrollToHour,
                            hours = viewState.hours
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = skeleton.bottomSurfaceColor)
                        .weight(1F)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(color = skeleton.bottomSurfaceColor)
                )
            }
        }
    }
}
