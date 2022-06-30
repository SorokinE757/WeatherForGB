package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather

interface Repo {
    fun getWeatherList():List<Weather>
    fun getWeather(lat: Double, lon: Double):Weather
}