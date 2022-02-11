/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.volvo.techtask.R
import com.volvo.techtask.platform.BaseActivity
import com.volvo.techtask.screens.forecast.CitiesFragment
import com.volvo.techtask.screens.forecast.WeatherForecastFragment

/**
 * Activity holder for serving fragments as host class.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavHostController()
    }

    private fun setupNavHostController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
        val navController = navHostFragment.navController
    }

    /*private fun configureAndShowDefaultFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.main_content) as CitiesFragment?
        if(fragment == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.main_content, CitiesFragment.getMainActivityFragment())
                .commit()
        }
    }*/



}
