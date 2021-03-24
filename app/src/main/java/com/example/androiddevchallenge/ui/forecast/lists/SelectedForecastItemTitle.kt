package com.example.androiddevchallenge.ui.forecast.lists

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectedForecastItemTitle(
    isSelected: Boolean,
    text: String
) {
    Crossfade(
        targetState = isSelected,
        animationSpec = tween(durationMillis = 600)
    ) { selected ->
        if (selected) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(color = MaterialTheme.colors.primary)
            ) {
                Title(
                    isSelected = true,
                    text = text
                )
            }
        } else {
            Title(
                isSelected = false,
                text = text
            )
        }
    }
}

@Composable
private fun Title(isSelected: Boolean, text: String) {
    val color = if (isSelected) {
        MaterialTheme.colors.onPrimary
    } else {
        MaterialTheme.colors.onSurface
    }

    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
        text = text,
        style = MaterialTheme.typography.caption,

        color = color,
        fontWeight = FontWeight.Bold,
    )
}