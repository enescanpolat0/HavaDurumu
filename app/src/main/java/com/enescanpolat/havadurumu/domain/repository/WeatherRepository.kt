package com.enescanpolat.havadurumu.domain.repository

import com.enescanpolat.havadurumu.domain.weather.WeatherInfo
import com.enescanpolat.havadurumu.util.Resource

interface WeatherRepository {

    suspend fun getWeatherData(lat:Double,long:Double): Resource<WeatherInfo>

}