/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.di

import com.volvo.techtask.platform.SharedPreferenceHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Shared Preference DI Module.
 *
 */
val SharedPrefDependency = module {

    factory { SharedPreferenceHelper(androidContext()) }
}