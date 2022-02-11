/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.forecast


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.volvo.techtask.R
import com.volvo.techtask.constants.IS_DEBUG_MODE
import com.volvo.techtask.models.forecast.ForecastData
import com.volvo.techtask.platform.BaseFragment
import com.volvo.techtask.platform.BaseViewModelFactory
import com.volvo.techtask.platform.LiveDataWrapper
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt


/**
 * Weather Forecast [Fragment].
 * Handles UI.
 * Fires rest api initiation.
 */
class WeatherForecastFragment : BaseFragment() {

    companion object{
        fun getWeatherForecastFragment() = WeatherForecastFragment()
    }

    // variables for accessing views
    private lateinit var mLocationName: TextView
    private lateinit var mWeatherState: TextView
    private lateinit var mWeatherImage: ImageView
    private lateinit var mTemperature: TextView
    private lateinit var mWindDirection: TextView
    private lateinit var mHumidity: TextView
    private lateinit var mtWindSpeed: TextView

    // Class variables
    private val mWeatherForecastUseCase : WeatherForecastUseCase by inject()
    private val mBaseViewModelFactory : BaseViewModelFactory =
        BaseViewModelFactory(Dispatchers.Main, Dispatchers.IO, mWeatherForecastUseCase)
    private val TAG = WeatherForecastFragment:: class.java.simpleName

    private val mViewModel: WeatherForecastFragmentViewModel by lazy {
        ViewModelProviders.of(this, mBaseViewModelFactory)
            .get(WeatherForecastFragmentViewModel::class.java)    }

    // Life Cycle
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().title = "Weather Forecast- Tomorrow"

        val args = arguments
        val woeId: Int = args?.get("woeId") as Int
        // start observing the targets
        this.mViewModel.mWeatherForecastResponse.observe(viewLifecycleOwner, this.mDataObserver)
        this.mViewModel.mLoadingLiveData.observe(viewLifecycleOwner, this.loadingObserver)
        this.mViewModel.requestCityWeatherForecastData(woeId)

        bindViews()

    }

    private fun bindViews() {
        mLocationName = requireView().findViewById(R.id.txtLocationName)
        mWeatherState = requireView().findViewById(R.id.txtWeatherState)
        mWeatherImage = requireView().findViewById(R.id.imgWeatherImage)
        mTemperature = requireView().findViewById(R.id.txtTemperature)
        mHumidity = requireView().findViewById(R.id.txtHumidity)
        mtWindSpeed = requireView().findViewById(R.id.txtWindSpeed)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    //---------------Observers---------------//
    private val mDataObserver = Observer<LiveDataWrapper<ForecastData>> { result ->
        when (result?.responseStatus) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                // Error for http request
                if (IS_DEBUG_MODE)
                    logD(TAG, "LiveDataResult.Status.ERROR = ${result.response}")
                error_holder.visibility = View.VISIBLE
                showToast("Error in getting data")
            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                if (IS_DEBUG_MODE)
                    logD(TAG, "LiveDataResult.Status.SUCCESS = ${result.response}")
                val mainDataReceived = result.response as ForecastData
                processData(mainDataReceived)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_cities

    /**
     * Handle success data
     */
    private fun processData(data: ForecastData) {
        if(IS_DEBUG_MODE) {
            logD(TAG, "processData called with data ${data.title}")
            logD(TAG, "processData listItems =  $data")
        }

        val refresh = Handler(Looper.getMainLooper())
        refresh.post {
            //mWindDirection.text = "wind direction: ${data.ConsolidatedWeathers[1].windDirectionCompass}"
            mHumidity.text = "humidity: ${data.ConsolidatedWeathers[1].humidity} %"
            mtWindSpeed.text = "wind speed: ${data.ConsolidatedWeathers[1].windSpeed.roundToInt()} mph"
            mLocationName.text = data.parent.title + ", " + data.title
            mWeatherState.text = data.ConsolidatedWeathers[1].weatherStateName
            mTemperature.text = "${data.ConsolidatedWeathers[1].theTemp.roundToInt()} \u2103"
            val url = "https://www.metaweather.com/static/img/weather/png/${data.ConsolidatedWeathers[1].weatherStateAbbr}.png"
            loadImageURL(requireContext(), mWeatherImage, url)
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
