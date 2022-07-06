package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather
import com.example.weatherforgb.domain.getRussianCities
import com.example.weatherforgb.domain.getWorldCities

class RepoRemoteImp : RepoSingleWeather {

    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread {
            Thread.sleep(300L)
        }.start()
        return Weather()
    }
}