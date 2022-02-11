/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.exceptions

/**
 * Class for holding network processing error.
 */
class NetworkError(errorDetail: String) : Exception(errorDetail)