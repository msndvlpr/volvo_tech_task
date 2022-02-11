/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.forecast

import com.volvo.techtask.models.forecast.ForecastData
import com.volvo.techtask.repository.weather_forecast.WeatherForecastRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Use Case class for handling Weather Forecast flow for selected city.
 * Requests data from Repo.
 * Process received data into required model and reverts back to ViewModel.
 */
class WeatherForecastUseCase: KoinComponent {

    private val mForecastRepo: WeatherForecastRepository by inject()

    suspend fun processWeatherForecastUseCase(cityId: Int): ForecastData {

        val forecast: ForecastData = mForecastRepo.getForecastData(cityId)
        return forecast
    }
}