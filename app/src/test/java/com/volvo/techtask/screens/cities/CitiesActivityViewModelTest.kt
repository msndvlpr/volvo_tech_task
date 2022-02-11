package com.volvo.techtask.screens.cities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.volvo.techtask.base.BaseUTTest
import com.volvo.techtask.di.configureTestAppComponent
import com.volvo.techtask.models.city.City
import com.volvo.techtask.platform.LiveDataWrapper
import com.volvo.techtask.screens.forecast.CitiesFragmentViewModel
import com.volvo.techtask.screens.forecast.CitiesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class CitiesActivityViewModelTest: BaseUTTest(){

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mCitiesFragmentViewModel: CitiesFragmentViewModel

    val mDispatcher = Dispatchers.Unconfined

    @MockK
    lateinit var mUserCase: CitiesUseCase

    private val mParam = listOf("Gothenburg", "Stockholm", "Mountain View", "London", "New York", "Berlin")

    @Before
    fun start(){
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_cities_view_model_data_populates_expected_value(){

        mCitiesFragmentViewModel = CitiesFragmentViewModel(mDispatcher,mDispatcher,mUserCase)
        val sampleResponse = getJson("success_cities_resp_list.json")

        var jsonObj = Gson().fromJson(sampleResponse, City:: class.java)

        //Make sure cities use case returns expected response when called
        coEvery { mUserCase.processCitiesDataUseCase(any()).first() } returns jsonObj
        mCitiesFragmentViewModel.mAllCitiesResponse.observeForever {  }

        mCitiesFragmentViewModel.requestCitiesActivityData(mParam)

        assert(mCitiesFragmentViewModel.mAllCitiesResponse.value != null)
        assert(mCitiesFragmentViewModel.mAllCitiesResponse.value!!.responseStatus
                == LiveDataWrapper.RESPONSESTATUS.SUCCESS)
        val testResult = mCitiesFragmentViewModel.mAllCitiesResponse.value as LiveDataWrapper<ArrayList<City>>
    }
}