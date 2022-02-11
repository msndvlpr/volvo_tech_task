package com.volvo.techtask.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.volvo.techtask.base.BaseUTTest
import com.volvo.techtask.di.configureTestAppComponent
import com.volvo.techtask.network.city.CitiesApiService
import com.volvo.techtask.repository.city.CitiesRepository
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
class CitiesRepositoryTest : BaseUTTest(){

    //Target
    private lateinit var mRepo: CitiesRepository
    //Inject api service created with koin
    val mAPIService : CitiesApiService by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mParam = "ab"
    val mCount = 6

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_cities_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("success_cities_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = CitiesRepository()

        val dataReceived = mRepo.getCitiesData(mParam)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, mCount)
    }
}