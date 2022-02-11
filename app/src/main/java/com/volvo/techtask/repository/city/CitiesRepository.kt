/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.repository.city

import com.volvo.techtask.models.city.City
import com.volvo.techtask.network.city.CitiesApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Repository for Cities Info Flow.
 * Requests data from either Network or DB.
 *
 */
class CitiesRepository: KoinComponent {

    private val mCitiesApiService: CitiesApiService by inject()
    /**
     * Request data from backend
     */
    suspend fun getCitiesData(query: String): ArrayList<City> {

        return processDataFetchLogic(query)

    }

    private suspend fun processDataFetchLogic(parameter: String): ArrayList<City> {

        val dataReceived = mCitiesApiService.getCitiesByName(parameter)

        return dataReceived
    }


}