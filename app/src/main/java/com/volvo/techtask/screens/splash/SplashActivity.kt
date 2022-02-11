/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.volvo.techtask.R
import com.volvo.techtask.platform.BaseActivity
import com.volvo.techtask.screens.home.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

/**
 * Activity holder for Splash Page.
 */
class SplashActivity : BaseActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handleSplashDelay()
    }

    private fun handleSplashDelay() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
