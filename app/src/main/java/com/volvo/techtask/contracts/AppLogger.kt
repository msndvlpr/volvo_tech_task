/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.contracts

/**
 * Common util for logging events.
 * Can be used to store logs locally as well.
 */
interface AppLogger {
    //May extend the functionality for accumulating log files into device memory as well
    fun logD(tag: String, message: String)

    fun logE(tag: String, message: String)

    fun logV(tag: String, message: String)

    fun logI(tag: String, message: String)
}