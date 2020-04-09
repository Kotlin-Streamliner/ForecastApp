package com.thisisstreamliner.forecastmvvm.ui.weather.current

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.thisisstreamliner.forecastmvvm.R
import com.thisisstreamliner.forecastmvvm.data.WeatherStackApiService
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.*

class   CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService =  WeatherStackApiService()

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeatherResponse = apiService.getCurrentWeather("Boston", "f")
            textView.text = currentWeatherResponse.toString()
            Log.e("TAG", "the temperature is ${currentWeatherResponse}")
        }

    }

}
