package com.thisisstreamliner.forecastmvvm.data.remote

import androidx.lifecycle.LiveData
import com.thisisstreamliner.forecastmvvm.data.remote.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String)
}