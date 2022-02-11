/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.models.city

import com.google.gson.annotations.SerializedName

data class City constructor(val title: String,
                            @SerializedName("location_type") val locationType: String,
                            @SerializedName("woeid") val woeId: Int,
                            @SerializedName("latt_long") val latLong: String
) {}
