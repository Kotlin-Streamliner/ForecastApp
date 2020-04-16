package com.thisisstreamliner.forecastmvvm.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.thisisstreamliner.forecastmvvm.data.db.CurrentWeatherDao
import com.thisisstreamliner.forecastmvvm.data.db.unitiocalized.UnitSpecificCurrentWeather
import com.thisisstreamliner.forecastmvvm.data.remote.WeatherNetworkDataSource
import com.thisisstreamliner.forecastmvvm.data.remote.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeaherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            // persist
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather> {
        return withContext(Dispatchers.IO) {
            return@withContext if(metric) currentWeaherDao.getWeatherMetric()
            else currentWeaherDao.getWeatherImperial()
        }
    }

    @SuppressLint("NewApi")
    private suspend fun initWeatherData(){
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather("London")
    }

    @SuppressLint("NewApi")
    private fun isFetchCurrentNeeded(lastFetchTime : ZonedDateTime) : Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeaherDao.upsert(fetchedWeather.current)
        }
    }
}