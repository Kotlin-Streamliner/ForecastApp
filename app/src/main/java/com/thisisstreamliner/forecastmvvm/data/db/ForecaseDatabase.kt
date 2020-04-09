package com.thisisstreamliner.forecastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thisisstreamliner.forecastmvvm.data.db.entity.CurrentWeatherEntry

@Database(entities =  [CurrentWeatherEntry::class ], version = 1)
abstract class ForecaseDatabase : RoomDatabase()  {
    abstract fun currentWeatherDao() : CurrentWeatherDao

    companion object {
        @Volatile  private var instance : ForecaseDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ForecaseDatabase::class.java,
            "forecast.db")
            .build()
    }
}