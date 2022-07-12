package com.example.weatherforgb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherforgb.databinding.ActivityMainBinding
import com.example.weatherforgb.ui.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commitNow()
        }
    }
}