package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.data.WeatherRepository

object ServiceLocator {
    val weatherRepository = WeatherRepository()
}