package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class StopPointItem(

    @SerializedName("naptanId") var naptanId: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("commonName") var commonName: String? = null,
)