/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.di

import com.volvo.techtask.screens.forecast.CitiesUseCase
import com.volvo.techtask.screens.forecast.WeatherForecastUseCase
import org.koin.dsl.module

/**
 * Use case DI module.
 * Provide Use case dependency.
 */
val UseCaseDependency = module {

    factory { CitiesUseCase() }
    factory { WeatherForecastUseCase() }
}