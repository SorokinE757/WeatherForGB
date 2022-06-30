package com.example.weatherforgb.ui.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherforgb.viewmodel.AppState

class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {


    fun getLiveData(): MutableLiveData<AppState> {
        chooseRepository()
        return liveData
    }

    private fun chooseRepository() {
        TODO("Not yet implemented")
    }

    fun sentRequest(): Boolean{
        return false
    }
}