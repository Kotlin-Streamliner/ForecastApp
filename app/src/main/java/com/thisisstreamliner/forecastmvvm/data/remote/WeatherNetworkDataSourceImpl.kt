package com.thisisstreamliner.forecastmvvm.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thisisstreamliner.forecastmvvm.data.WeatherStackApiService
import com.thisisstreamliner.forecastmvvm.data.remote.response.CurrentWeatherResponse
import com.thisisstreamliner.forecastmvvm.internal.NoConnectivityException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherNetworkDataSourceImpl(
    private val weatherStackApiService: WeatherStackApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val fetchedCurrentWeather = weatherStackApiService.getCurrentWeather(location, "f")
                _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
            }
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection .", e)
        }
    }
}