/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022

package com.volvo.techtask.screens.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.volvo.techtask.models.city.City
import com.volvo.techtask.platform.LiveDataWrapper
import com.volvo.techtask.screens.forecast.CitiesUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

/**
 * Cities View Model.
 * Handles connecting with corresponding Use Case.
 * Getting back data to View.
 */

class CitiesFragmentViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher,
    private val useCase: CitiesUseCase
) : ViewModel(), KoinComponent
{
    var mAllCitiesResponse = MutableLiveData<LiveDataWrapper<ArrayList<City>>>()
    val mLoadingLiveData = MutableLiveData<Boolean>()
    private val job = SupervisorJob()
    val mUiScope = CoroutineScope(mainDispatcher + job)
    val mIoScope = CoroutineScope(ioDispatcher + job)

    init {
        //call reset to reset all VM data as required
        resetValues()
    }

    //Reset any member as per flow
    private fun resetValues() {

    }

    //can be called from View explicitly if required, Keep default scope
    fun requestCitiesActivityData(param: List<String>) {
        if(mAllCitiesResponse.value == null){
            mUiScope.launch {
                mAllCitiesResponse.value = LiveDataWrapper.loading()
                setLoadingVisibility(true)
                try {
                    val data = mIoScope.async { return@async useCase.processCitiesDataUseCase(param)}.await()
                    try {
                        mAllCitiesResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
                    setLoadingVisibility(false)
                } catch (e: Exception) {
                    setLoadingVisibility(false)
                    mAllCitiesResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        mLoadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}