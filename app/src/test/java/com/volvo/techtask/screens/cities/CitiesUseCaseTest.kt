package com.volvo.techtask.screens.cities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.volvo.techtask.base.BaseUTTest
import com.volvo.techtask.di.configureTestAppComponent
import com.volvo.techtask.network.city.CitiesApiService
import com.volvo.techtask.repository.city.CitiesRepository
import com.volvo.techtask.screens.forecast.CitiesUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class CitiesUseCaseTest : BaseUTTest(){

    //Target
    private lateinit var mCitiesUseCase: CitiesUseCase
    //Inject cities repo created with koin
    val mRepo : CitiesRepository by inject()

    //Inject api service created with koin
    val mAPIService : CitiesApiService by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mParam = listOf("Gothenburg", "Stockholm", "Mountain View", "London", "New York", "Berlin")

    val mCount = 6

    @Before
    fun start(){
        super.setUp()
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_cities_use_case_returns_expected_value()= runBlocking{

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mCitiesUseCase = CitiesUseCase()

        val dataReceived = mCitiesUseCase.processCitiesDataUseCase(mParam)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, mCount)
        //assertEquals(dataReceived.next, mNextValue)
    }

}