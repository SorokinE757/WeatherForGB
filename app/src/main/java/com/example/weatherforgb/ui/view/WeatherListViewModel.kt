package com.example.weatherforgb.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforgb.model.Repo
import com.example.weatherforgb.model.RepoLocalImp
import com.example.weatherforgb.model.RepoRemoteImp
import com.example.weatherforgb.viewmodel.AppState

class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    lateinit var repo: Repo

    fun getLiveData(): MutableLiveData<AppState> {
        chooseRepository()
        return liveData
    }

    private fun chooseRepository() {
        repo = if (isConnection()) {
            RepoLocalImp()
        } else {
            RepoRemoteImp()
        }
    }

    private fun isConnection(): Boolean {
        return false
    }

    fun sentRequest() {
        liveData.value = AppState.Loading
        if ((1..3).random() != 1) {
            liveData.setValue(AppState.Success(repo.getWeather(55.755826, 37.617299900000035)))

        } else {
            liveData.setValue(AppState.Error(error =  IllegalStateException("ошибочка вышла")))

        }
    }
}