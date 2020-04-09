package com.thisisstreamliner.forecastmvvm.data.remote.response

import com.thisisstreamliner.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.thisisstreamliner.forecastmvvm.data.db.entity.Location
import com.thisisstreamliner.forecastmvvm.data.db.entity.Request

data class CurrentWeatherResponse(
    val current: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)