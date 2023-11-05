package com.enescanpolat.havadurumu.presentation

import com.enescanpolat.havadurumu.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo : WeatherInfo?=null,
    val isloading:Boolean= false,
    val error:String?=""
)
