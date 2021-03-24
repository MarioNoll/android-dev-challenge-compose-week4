package com.example.androiddevchallenge.ui.input.search

interface SearchInputListener {
    fun onInputClicked()
    fun onInputChanged(input: String)
    fun onInputFocusLost()
    fun onSearchClicked()

    companion object {
        val NONE = object : SearchInputListener {
            override fun onInputClicked() {}

            override fun onInputChanged(input: String) {}

            override fun onInputFocusLost() {}

            override fun onSearchClicked() {}
        }
    }
}