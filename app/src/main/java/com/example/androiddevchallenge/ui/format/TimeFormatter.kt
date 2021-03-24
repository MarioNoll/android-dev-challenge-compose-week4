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
package com.example.androiddevchallenge.ui.format

import android.content.Context
import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun formatTime(time: LocalTime): String {
    val is24Hour = DateFormat.is24HourFormat(LocalContext.current)

    return DateTimeFormatter
        .ofPattern(if (is24Hour) "H:mm" else "h:mm a")
        .format(time)
}

@Composable
fun formatDateTime(day: LocalDate, time: LocalTime): String =
    LocalDateTime
        .of(day, time)
        .format(
            LocalContext.current,
            DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME
        )

@Composable
fun formatDate(day: LocalDate): String =
    LocalDateTime
        .of(day, LocalTime.MIN)
        .format(LocalContext.current, DateUtils.FORMAT_SHOW_WEEKDAY or DateUtils.FORMAT_SHOW_DATE)

private fun LocalDateTime.format(context: Context, flags: Int): String =
    DateUtils.formatDateTime(context, epochMillis(), flags)

private fun LocalDateTime.epochMillis() = 1000 * toEpochSecond(
    ZoneOffset.systemDefault()
        .rules
        .getOffset(this)
)
