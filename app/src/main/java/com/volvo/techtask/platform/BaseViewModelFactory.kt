/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.volvo.techtask.screens.forecast.CitiesFragmentViewModel
import com.volvo.techtask.screens.forecast.CitiesUseCase
import com.volvo.techtask.screens.forecast.WeatherForecastFragmentViewModel
import com.volvo.techtask.screens.forecast.WeatherForecastUseCase
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.KoinComponent

/**
 * Base VM Factory for creation of different VM's
 */

class BaseViewModelFactory constructor(
     private val mainDispatcher: CoroutineDispatcher
    ,private val ioDispatcher: CoroutineDispatcher
    ,private val useCase: KoinComponent
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass.isAssignableFrom(CitiesFragmentViewModel::class.java)) {
            CitiesFragmentViewModel(mainDispatcher, ioDispatcher, useCase as CitiesUseCase) as T
        } else if (modelClass.isAssignableFrom(WeatherForecastFragmentViewModel::class.java)){
            WeatherForecastFragmentViewModel(mainDispatcher, ioDispatcher, useCase as WeatherForecastUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}