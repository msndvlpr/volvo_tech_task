/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.repository.weather_forecast

import com.volvo.techtask.models.forecast.ForecastData
import com.volvo.techtask.network.weather_forecast.WeatherForecastApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Repository for WeatherForecast Flow.
 * Requests data from either Network or DB.
 *
 */
class WeatherForecastRepository : KoinComponent {

    private val mForecastApiService: WeatherForecastApiService by inject()
    /**
     * Request data from backend
     */
    suspend fun getForecastData(query: Int): ForecastData {

        return processDataFetchLogic(query)
    }

    private suspend fun processDataFetchLogic(parameter: Int): ForecastData{

        val dataReceived = mForecastApiService.getWeatherForecastDataByCityId(parameter)
        return dataReceived
    }


}