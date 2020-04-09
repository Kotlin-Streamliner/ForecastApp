package com.thisisstreamliner.forecastmvvm.data.response

data class CurrentWeatherResponse(
    val current: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)