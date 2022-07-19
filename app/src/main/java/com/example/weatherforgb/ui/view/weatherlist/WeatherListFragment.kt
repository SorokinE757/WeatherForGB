package com.example.weatherforgb.ui.view.weatherlist

import OnItemClick
import WeatherListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforgb.R
import com.example.weatherforgb.databinding.FragmentWeatherListBinding
import com.example.weatherforgb.domain.Weather
import com.example.weatherforgb.viewmodel.AppState
import com.example.weatherforgb.viewmodel.WeatherListViewModel
import com.google.android.material.snackbar.Snackbar

class WeatherListFragment : Fragment(), OnItemClick {

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    var isRussian = true

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    lateinit var viewModel: WeatherListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }
        })

        binding.weatherListFragmentFAB.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                viewModel.getWeatherListForRussia()
                binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getWeatherListForWorld()
                binding.weatherListFragmentFAB.setImageResource(R.drawable.ic_earth)
            }
        }
        viewModel.getWeatherListForRussia()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.showResult()
                binding.root.homeWork("Error", Snackbar.LENGTH_LONG, "Try again") { _ ->
                    if (isRussian) {
                        viewModel.getWeatherListForRussia()
                    } else {
                        viewModel.getWeatherListForWorld()
                    }
                }
            }
            AppState.Loading -> {
                binding.loading()
            }
            is AppState.SuccessSingle -> {
                binding.showResult()
                val result = appState.weatherData
            }
            is AppState.SuccessMulti -> {
                binding.showResult()
                binding.mainFragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
        }
    }

    fun View.homeWork(str: String, duration: Int, actionStr: String, block: (v:View) -> Unit){
        Snackbar.make(this,str, duration).setAction(actionStr, block).show()
    }

    fun FragmentWeatherListBinding.loading() {
        this.mainFragmentLoadingLayout.visibility = View.VISIBLE
        this.weatherListFragmentFAB.visibility = View.GONE
    }

    fun FragmentWeatherListBinding.showResult() {
        this.mainFragmentLoadingLayout.visibility = View.GONE
        this.weatherListFragmentFAB.visibility = View.VISIBLE
    }


    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().hide(this).add(
            R.id.container, DetailsFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}
