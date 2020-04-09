package com.thisisstreamliner.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thisisstreamliner.forecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.thisisstreamliner.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.thisisstreamliner.forecastmvvm.data.db.unitiocalized.ImperialCurrentWeatherEntry
import com.thisisstreamliner.forecastmvvm.data.db.unitiocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao  {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM `current_weather ` where id = $CURRENT_WEATHER_ID ")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM `current_weather ` where id = $CURRENT_WEATHER_ID ")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>
}