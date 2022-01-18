package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class ArrivalsItem(

    @SerializedName("lineId") var lineId: String? = null,
    @SerializedName("lineName") var lineName: String? = null,
    @SerializedName("direction") var direction: String? = null,
    @SerializedName("expectedArrival") var expectedArrival: String? = null,
    @SerializedName("id") var id: String? = null
)