package com.thisisstreamliner.forecastmvvm

import android.app.Application
import com.thisisstreamliner.forecastmvvm.data.WeatherStackApiService
import com.thisisstreamliner.forecastmvvm.data.db.ForecaseDatabase
import com.thisisstreamliner.forecastmvvm.data.remote.ConnectivityInterceptor
import com.thisisstreamliner.forecastmvvm.data.remote.ConnectivityInterceptorImpl
import com.thisisstreamliner.forecastmvvm.data.remote.WeatherNetworkDataSource
import com.thisisstreamliner.forecastmvvm.data.remote.WeatherNetworkDataSourceImpl
import com.thisisstreamliner.forecastmvvm.data.repository.ForecastRepository
import com.thisisstreamliner.forecastmvvm.data.repository.ForecastRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

        bind() from singleton { ForecaseDatabase(instance()) }
        bind() from singleton { instance<ForecaseDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherStackApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }

    }
}