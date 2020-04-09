package com.thisisstreamliner.forecastmvvm.data.db.unitiocalized

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry (
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    @ColumnInfo(name = "pressure")
    override val pressure: Double,
    @ColumnInfo(name = "wind_speed")
    override val windSpeed: Int,
    @ColumnInfo(name = "wind_dir")
    override val windDirection: String
) : UnitSpecificCurrentWeather