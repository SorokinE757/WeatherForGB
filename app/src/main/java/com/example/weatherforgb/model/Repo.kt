package com.example.weatherforgb.model

import com.example.weatherforgb.domain.Weather

fun interface RepoWeatherList {
    fun getWeatherList(location: Location):List<Weather>
}

fun interface RepoSingleWeather {
    fun getWeather(lat: Double, lon: Double):Weather
}

sealed class Location{
    object Russia: Location()
    object World: Location()

}