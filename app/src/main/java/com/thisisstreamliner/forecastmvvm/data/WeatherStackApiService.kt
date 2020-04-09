package com.thisisstreamliner.forecastmvvm.data

import com.thisisstreamliner.forecastmvvm.data.remote.response.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "173773ab2066290b21bd26845fd6f5ca"
const val BASE_URL = "http://api.weatherstack.com/"


// http://api.weatherstack.com/current?access_key=173773ab2066290b21bd26845fd6f5ca&query=NewYork

interface WeatherStackApiService {

    @GET("current")
    suspend fun getCurrentWeather(@Query("query")city: String, @Query("units") metric : String) : CurrentWeatherResponse

    companion object {
        operator fun invoke() : WeatherStackApiService {
             val requestInterceptor = Interceptor {chain ->
                  val url = chain.request()
                      .url()
                      .newBuilder()
                      .addQueryParameter("access_key", API_KEY)
                      //.addQueryParameter("language", "uk")
                      .build()
                  val request = chain.request()
                      .newBuilder()
                      .url(url)
                      .build()
                 return@Interceptor chain.proceed(request)
             }
             val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build()

            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherStackApiService::class.java)
        }
    }
}