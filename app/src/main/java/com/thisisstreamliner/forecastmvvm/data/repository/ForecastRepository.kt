package com.thisisstreamliner.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.thisisstreamliner.forecastmvvm.data.db.unitiocalized.UnitSpecificCurrentWeather

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeather>
}