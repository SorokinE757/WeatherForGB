package com.example.weatherforgb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforgb.model.*
import kotlin.random.Random

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
        if ((0..3).random(Random(System.currentTimeMillis())) == 1) {
            liveData.setValue(AppState.Error(error =  IllegalStateException("ошибочка вышла")))
        }else{
           liveData.postValue(AppState.SuccessMulti(repositoryMulti.getWeatherList(location)))

        }

    }

    private fun isConnection(): Boolean {
        return false
    }

}