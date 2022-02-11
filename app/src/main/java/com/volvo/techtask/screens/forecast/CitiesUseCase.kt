/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.forecast

import com.volvo.techtask.models.city.City
import com.volvo.techtask.repository.city.CitiesRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Use Case class for handling cities fetch flow
 * Requests data from Repo.
 * Process received data into required model and reverts back to ViewModel.
 */
class CitiesUseCase: KoinComponent {

    private val mCitiesRepo: CitiesRepository by inject()

    suspend fun processCitiesDataUseCase(citiesName: List<String>): ArrayList<City> {

        var list: ArrayList<City> = ArrayList()
        for(name in citiesName){
            val foundCity: City = mCitiesRepo.getCitiesData(name).first()
            list.add(foundCity)
        }
        return list
    }
}