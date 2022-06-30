package com.example.weatherforgb.viewmodel

import com.example.weatherforgb.domain.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}