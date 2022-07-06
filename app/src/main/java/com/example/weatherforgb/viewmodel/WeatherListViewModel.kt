package com.example.weatherforgb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforgb.model.RepoLocalImp
import com.example.weatherforgb.model.RepoRemoteImp
import com.example.weatherforgb.model.RepoSingleWeather
import com.example.weatherforgb.model.RepoWeatherList

class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    lateinit var repoWeatherList: RepoWeatherList
    lateinit var repoSingleWeather: RepoSingleWeather

    fun getLiveData(): MutableLiveData<AppState> {
        chooseRepository()
        return liveData
    }

    private fun chooseRepository() {
        repoSingleWeather = if (isConnection()) {
           RepoRemoteImp()
        } else {
            RepoLocalImp()
        }
        repoWeatherList = RepoLocalImp()
    }

    private fun isConnection(): Boolean {
        return false
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
        if ((1..3).random() != 1) {
            liveData.setValue(AppState.Success(repoSingleWeather.getWeather(55.755826, 37.617299900000035)))

        } else {
            liveData.setValue(AppState.Error(error =  IllegalStateException("ошибочка вышла")))

        }
    }
}