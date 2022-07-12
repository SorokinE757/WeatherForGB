package com.example.weatherforgb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforgb.model.*

class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel() {

    lateinit var repositoryMulti: RepoWeatherList
    lateinit var repositoryOne: RepoSingleWeather

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repositoryOne = if (isConnection()) {
            RepoRemoteImp()
        } else {
            RepoLocalImp()
        }
        repositoryMulti = RepoLocalImp()
    }

    fun getWeatherListForRussia() {
        sentRequest(Location.Russia)
    }

    fun getWeatherListForWorld() {
        sentRequest(Location.World)
    }

    private fun sentRequest(location: Location) {
        liveData.value = AppState.Loading
        if ((1..3).random() != 1) {
            liveData.postValue(AppState.SuccessMulti(repositoryMulti.getWeatherList(location)))
        }else{
            liveData.setValue(AppState.Error(error =  IllegalStateException("ошибочка вышла")))

        }

    }

    private fun isConnection(): Boolean {
        return false
    }

}