package com.example.weatherforgb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherforgb.databinding.ActivityMainBinding
import com.example.weatherforgb.ui.view.WeatherListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commitNow()
        }
    }
}