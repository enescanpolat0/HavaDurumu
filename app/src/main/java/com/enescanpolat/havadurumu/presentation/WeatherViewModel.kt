package com.enescanpolat.havadurumu.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enescanpolat.havadurumu.domain.location.LocationTracker
import com.enescanpolat.havadurumu.domain.repository.WeatherRepository
import com.enescanpolat.havadurumu.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) :ViewModel(){

    var state by mutableStateOf(WeatherState())
        private set


    fun loadWeatherInfo(){

        viewModelScope.launch {
            state = state.copy(
                isloading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->

                when(val result = repository.getWeatherData(lat = location.latitude,long = location.longitude)){
                    is Resource.Success->{
                        state = state.copy(
                            weatherInfo = result.data,
                            isloading = false,
                            error = null
                        )
                    }
                    is Resource.Loading->{
                        state = state.copy(
                            weatherInfo = null,
                            isloading = true,
                            error = null
                        )

                    }
                    is Resource.Error->{

                        state = state.copy(
                            weatherInfo = null,
                            isloading = true,
                            error = result.message
                        )

                    }
                }

            }
        }

    }

}