/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.di


import com.volvo.techtask.repository.city.CitiesRepository
import com.volvo.techtask.repository.weather_forecast.WeatherForecastRepository
import org.koin.dsl.module

/**
 * Repository DI module.
 * Provides Repo dependency.
 */
val RepoDependency = module {

    factory { WeatherForecastRepository() }
    factory { CitiesRepository() }

}