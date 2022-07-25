package com.example.weatherforgb.ui.view.details

import com.example.weatherforgb.model.dto.WeatherDTO

fun interface OnResponse {
    fun onResponse(weather: WeatherDTO)
}