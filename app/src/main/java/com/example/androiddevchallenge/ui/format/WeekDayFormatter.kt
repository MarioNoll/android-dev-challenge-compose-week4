package com.example.androiddevchallenge.ui.format

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.TextStyle

@Suppress("DEPRECATION")
@Composable
fun formatWeekDay(date: LocalDate): String = date.dayOfWeek.getDisplayName(
    TextStyle.SHORT,
    LocalContext.current.resources.configuration.locale
)
