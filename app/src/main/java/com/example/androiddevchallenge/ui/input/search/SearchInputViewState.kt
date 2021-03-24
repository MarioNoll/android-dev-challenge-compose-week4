package com.example.androiddevchallenge.ui.input.search

data class SearchInputViewState(
    val city: String,
    val showCity: Boolean,
    val isFocused: Boolean,
    val isLoading: Boolean
) {
    companion object {
        val DEFAULT = SearchInputViewState(
            city = "",
            showCity = false,
            isFocused = false,
            isLoading = false
        )
    }
}