package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather

class RepoRemoteImp : Repo {
    override fun getWeatherList(): List<Weather> {
        Thread {
            Thread.sleep(200L)
        }.start()
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread {
            Thread.sleep(300L)
        }.start()
        return Weather()
    }
}