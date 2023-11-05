package com.enescanpolat.havadurumu.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.enescanpolat.havadurumu.data.mappers.toWeatherInfo
import com.enescanpolat.havadurumu.data.remote.WeatherAPI
import com.enescanpolat.havadurumu.domain.repository.WeatherRepository
import com.enescanpolat.havadurumu.domain.weather.WeatherInfo
import com.enescanpolat.havadurumu.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api:WeatherAPI
):WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
       return try {

           Resource.Success(
               data = api.getWeatherData(
                   lat = lat,
                   long = long
               ).toWeatherInfo()
           )

       }catch (e:Exception){
           e.printStackTrace()
           Resource.Error(e.localizedMessage?:"An Unknown error accured ")
       }
    }
}