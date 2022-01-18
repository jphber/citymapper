package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class StopPointSequence(

    @SerializedName("lineId") var lineId: String? = null,
    @SerializedName("lineName") var lineName: String? = null,
    @SerializedName("direction") var direction: String? = null,
    @SerializedName("stopPoint") var stopPoint: ArrayList<StopPointX> = arrayListOf(),

    )