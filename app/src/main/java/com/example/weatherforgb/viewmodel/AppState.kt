package com.example.weatherforgb.viewmodel

import com.example.weatherforgb.domain.Weather

sealed class AppState {
    data class SuccessSingle(val weatherData: Weather) : AppState()
    data class SuccessMulti(val weatherList: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}