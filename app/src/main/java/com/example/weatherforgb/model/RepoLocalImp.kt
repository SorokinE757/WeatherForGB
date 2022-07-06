package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather
import com.example.weatherforgb.domain.getRussianCities
import com.example.weatherforgb.domain.getWorldCities

class RepoLocalImp : RepoWeatherList, RepoSingleWeather {
    override fun getWeatherList(location: Location): List<Weather> {
        return when (location) {
            Location.Russia -> {
                getRussianCities()
            }
            Location.World -> {
                getWorldCities()
            }
        }
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}