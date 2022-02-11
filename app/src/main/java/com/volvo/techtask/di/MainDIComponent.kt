/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.di

/**
 * Main dependency component.
 * This will create and provide required dependencies with sub dependencies.
 */
val appComponent = listOf(UseCaseDependency, AppUtilDependency, NetworkDependency, SharedPrefDependency, RepoDependency)