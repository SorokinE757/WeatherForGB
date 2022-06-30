package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather

class RepoLocalImp: Repo {
    override fun getWeatherList(): List<Weather> {
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}