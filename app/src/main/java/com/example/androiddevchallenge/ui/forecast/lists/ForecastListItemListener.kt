package com.example.androiddevchallenge.ui.forecast.lists

interface ForecastListItemListener {
    fun onDaySelected(index: Int)
    fun onHourSelected(index: Int)

    companion object {
        val NONE = object : ForecastListItemListener {
            override fun onDaySelected(index: Int) {}

            override fun onHourSelected(index: Int) {}
        }
    }
}

