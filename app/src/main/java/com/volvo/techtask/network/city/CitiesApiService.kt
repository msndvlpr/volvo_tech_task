/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.network.city

import com.volvo.techtask.models.city.City
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * City info service Retrofit API.
 */
interface CitiesApiService{

    @GET("location/search")
    suspend fun getCitiesByName(@Query("query") cityKeyword: String): ArrayList<City>

}