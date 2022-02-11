/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.models.forecast

import com.google.gson.annotations.SerializedName


data class ForecastData constructor(@SerializedName("consolidated_weather") val ConsolidatedWeathers: List<ConsolidatedWeather>,
                                    val time: String,
                                    @SerializedName("sun_rise") val sunRise: String,
                                    @SerializedName("sun_set") val sunSet: String,
                                    @SerializedName("timezone_name") val timezoneName: String,
                                    val parent: Parent,
                                    val sources: List<Source>,
                                    val title: String,
                                    @SerializedName("locationType") val location_type: String,
                                    @SerializedName("woeid") val woeId: Int,
                                    @SerializedName("latt_long") val latLong: String,
                                    @SerializedName("timezone") val timeZone: String
){}

