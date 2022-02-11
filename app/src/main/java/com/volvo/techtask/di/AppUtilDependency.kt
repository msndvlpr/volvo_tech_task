/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.di

import com.volvo.techtask.utils.AppUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * DI module for App Util dependency.
 */
val AppUtilDependency = module {

    factory { AppUtils(androidContext()) }
}