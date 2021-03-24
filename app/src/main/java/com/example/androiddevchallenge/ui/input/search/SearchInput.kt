package com.example.androiddevchallenge.ui.input.search

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.input.updateInputTransitionData

@Composable
fun CitySearchInput(
    listener: SearchInputListener,
    viewState: SearchInputViewState,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val transitionData = updateInputTransitionData(showCity = viewState.showCity)

    if (viewState.isLoading) {
        LocalFocusManager.current.clearFocus()
    }

    if (viewState.isFocused) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(50))
            .background(color = MaterialTheme.colors.surface)
            .let {
                if (!viewState.isLoading) {
                    it.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = MaterialTheme.colors.onSurface),
                        role = Role.Button,
                        onClickLabel = stringResource(id = R.string.search_input_click_label),
                        onClick = { listener.onInputClicked() }
                    )
                } else {
                    it
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {

            Box(
                modifier = Modifier.weight(1F),
                contentAlignment = Alignment.CenterStart,
            ) {
                CityTextField(
                    modifier = Modifier
                        .alpha(alpha = transitionData.textFieldAlpha)
                        .onFocusChanged {
                            if (it == FocusState.Inactive) {
                                listener.onInputFocusLost()
                            }
                        }
                        .focusRequester(focusRequester),
                    enabled = viewState.showCity,
                    city = viewState.city,
                    listener = listener
                )

                Placeholder(
                    modifier = Modifier.alpha(alpha = transitionData.placeholderAlpha)
                )
            }

            Crossfade(targetState = viewState.isLoading) { isLoading ->
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(70.dp)
                        .padding(start = 24.dp, end = 16.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp)
                        )

                    } else {
                        FloatingActionButton(
                            modifier = Modifier.size(30.dp),
                            backgroundColor = MaterialTheme.colors.primaryVariant,
                            contentColor = MaterialTheme.colors.onPrimary,
                            onClick = { listener.onSearchClicked() }
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Outlined.Search,
                                contentDescription = stringResource(id = R.string.search_button_content_description)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CityTextField(
    modifier: Modifier,
    enabled: Boolean,
    city: String,
    listener: SearchInputListener
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier.alpha(1F),
        singleLine = true,
        value = city,
        enabled = enabled,
        textStyle = MaterialTheme.typography.body1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
            listener.onSearchClicked()
        }),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = listener::onInputChanged
    )
}

@Composable
private fun Placeholder(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.search_input_placeholder_title),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            modifier = Modifier.alpha(0.5F),
            text = stringResource(id = R.string.search_input_placeholder_subtitle),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption
        )

    }
}
