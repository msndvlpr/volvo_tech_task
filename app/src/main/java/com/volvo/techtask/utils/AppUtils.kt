/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Common Utilities for App
 */
class AppUtils(val context: Context) {

    val isRunningTestMode : Boolean by lazy {
        try {
            Class.forName("android.support.test.espresso.Espresso")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}