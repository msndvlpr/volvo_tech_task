/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.models.forecast

import com.google.gson.annotations.SerializedName


data class Source constructor(val title: String,
                              val slug: String,
                              val url: String,
                              @SerializedName("crawl_rate") val crawlRate: String
) {}
