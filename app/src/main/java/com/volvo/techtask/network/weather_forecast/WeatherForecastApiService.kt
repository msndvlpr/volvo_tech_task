/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.network.weather_forecast

import com.volvo.techtask.models.forecast.ForecastData
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Forecast service Retrofit API.
 */
interface WeatherForecastApiService{

    @GET("location/{id}")
    suspend fun getWeatherForecastDataByCityId(@Path("id") id: Int): ForecastData

}