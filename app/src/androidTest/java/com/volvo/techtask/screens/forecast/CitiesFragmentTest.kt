

import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.volvo.techtask.R
import com.volvo.techtask.base.BaseUITest
import com.volvo.techtask.di.generateTestAppComponent
import com.volvo.techtask.helpers.recyclerItemAtPosition
import com.volvo.techtask.screens.forecast.CitiesFragment
import com.volvo.techtask.screens.forecast.CitiesRecyclerViewAdapter
import com.volvo.techtask.screens.forecast.CitiesUseCase
import com.volvo.techtask.screens.home.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4:: class)
class CitiesFragmentTest : BaseUITest(){

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity:: class.java, false, false)

    //Inject cities use case created with koin
    val mCitiesUseCase : CitiesUseCase by inject()

    //Inject Mock Web Server created with koin
    val  mMockWebServer : MockWebServer by inject()

    private val mCityTitleTestOne = "london"
    //private val mCityWeoIdTestOne = 44418
    private val mCityTitleTestTwo = "Stockholm"
    //private val mCityWeoIdTestTwo = 906057

    @Before
    fun start(){
        super.setUp()
        loadKoinModules(generateTestAppComponent(getMockWebServerUrl()).toMutableList())
    }

    @Test
    fun test_recyclerview_elements_for_expected_response() {
        mActivityTestRule.launchActivity(null)

        mockNetworkResponseWithFileContent("success_cities_resp_list.json", HttpURLConnection.HTTP_OK)

        //Wait for MockWebServer to get back with response
        SystemClock.sleep(3000)

        //Check if item at 0th position is having 0th element in json
        onView(withId(R.id.landingListRecyclerView)).check(matches(recyclerItemAtPosition(0, ViewMatchers.hasDescendant(withText("a")))))

        /*onView(withId(R.id.landingListRecyclerView))
            .check(matches(
                    recyclerItemAtPosition(
                        3, ViewMatchers.hasDescendant(withText(mCityWeoIdTestOne)))))*/

        //Check if item at 3rd position is having 3rd element in json
        /////////onView(withId(R.id.landingListRecyclerView)).check(matches(recyclerItemAtPosition(1, ViewMatchers.hasDescendant(withText(mCityTitleTestTwo)))))

        //Check for 4th position
        /*onView(withId(R.id.landingListRecyclerView))
            .check(matches(recyclerItemAtPosition(1, ViewMatchers.
            hasDescendant(withText(mCityWeoIdTestTwo)))))
*/
    }
}