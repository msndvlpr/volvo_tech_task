/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.forecast


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.volvo.techtask.R
import com.volvo.techtask.constants.IS_DEBUG_MODE
import com.volvo.techtask.models.city.City
import com.volvo.techtask.platform.BaseFragment
import com.volvo.techtask.platform.BaseViewModelFactory
import com.volvo.techtask.platform.LiveDataWrapper
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

/**
 * Cities [Fragment].
 * Handles UI.
 * Fires rest api initiation.
 */
class CitiesFragment : BaseFragment() {

    companion object{
        fun getCitiesFragment() = CitiesFragment()
    }

    // variables for accessing views
    private lateinit var pageTitle: TextView

    // Class variables
    private val mCitiesUseCase : CitiesUseCase by inject()
    private val mBaseViewModelFactory : BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO, mCitiesUseCase)
    private val TAG = CitiesFragment:: class.java.simpleName
    lateinit var mRecyclerViewAdapter: CitiesRecyclerViewAdapter
    // Cities list provided by the project boundaries
    private val predefinedCityNames = listOf("Gothenburg", "Stockholm", "Mountain View", "London", "New York", "Berlin")

    private val mViewModel : CitiesFragmentViewModel by lazy {
        ViewModelProviders.of(this, mBaseViewModelFactory)
            .get(CitiesFragmentViewModel::class.java)    }

    //Life Cycle
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().title = "Weather Forecast Cities"

        //Start observing the targets
        this.mViewModel.mAllCitiesResponse.observe(viewLifecycleOwner, this.mDataObserver)
        this.mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)

        mRecyclerViewAdapter = CitiesRecyclerViewAdapter(requireActivity(), arrayListOf())
        landingListRecyclerView.adapter = mRecyclerViewAdapter
        landingListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        this.mViewModel.requestCitiesActivityData(predefinedCityNames)

        pageTitle = requireView().findViewById(R.id.txtTitleCities)
    }

    // Observers
    private val mDataObserver = Observer<LiveDataWrapper<ArrayList<City>>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for http request
                if(IS_DEBUG_MODE)
                    logE(TAG,"LiveDataResult.Status.ERROR = ${result.error}")
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                if(IS_DEBUG_MODE)
                    logD(TAG,"LiveDataResult.Status.SUCCESS = ${result.response}")
                val mainItemReceived = result.response as ArrayList<City>
                //val  listItems =  mainItemReceived as ArrayList<City>
                processData(mainItemReceived)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_cities

    /**
     * Handle success data
     */
    private fun processData(listItems: ArrayList<City>) {
        if(IS_DEBUG_MODE) {
            logD(TAG,"processData called with data ${listItems.size}")
            logD(TAG,"processData listItems =  $listItems")
        }

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            pageTitle.visibility = View.VISIBLE
            mRecyclerViewAdapter.updateListItems(listItems)
        }
    }

    /**
     * Hide/show loader
     */
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide progress bar
        if(visible){
            progress_circular.visibility = View.VISIBLE
        } else{
            progress_circular.visibility = View.INVISIBLE
        }
    }
}
