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
                Toast.makeText(requireContext(), appState.error.toString(), Toast.LENGTH_LONG)
                    .show()
                binding.mainFragmentLoadingLayout.visibility = View.GONE
            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.SuccessSingle -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val result = appState.weatherData
            }
            is AppState.SuccessMulti -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                binding.mainFragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
        }
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().hide(this).add(
            R.id.container, DetailsFragment.newInstance(weather)
        ).addToBackStack("").commit()
    }
}
