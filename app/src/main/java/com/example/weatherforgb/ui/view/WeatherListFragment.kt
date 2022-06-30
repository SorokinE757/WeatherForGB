package com.example.weatherforgb.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforgb.R
import com.example.weatherforgb.databinding.FragmentWeatherListBinding

class WeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    lateinit var binding: FragmentWeatherListBinding
    private lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }


}