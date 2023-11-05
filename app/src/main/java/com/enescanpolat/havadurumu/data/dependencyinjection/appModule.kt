package com.enescanpolat.havadurumu.data.dependencyinjection

import android.app.Application
import com.enescanpolat.havadurumu.data.remote.WeatherAPI
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {


    @Singleton
    @Provides
    fun injectWeatherAPI():WeatherAPI{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun injectFusedLocationProviderClient(application:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(application)
    }



}