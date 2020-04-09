package com.thisisstreamliner.forecastmvvm.data.db.unitiocalized

interface UnitSpecificCurrentWeather {
    val temperature : Double
    val pressure : Double
    val windSpeed : Int
    val windDirection : String
}