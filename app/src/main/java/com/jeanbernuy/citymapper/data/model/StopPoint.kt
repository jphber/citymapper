package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class StopPoint(

    @SerializedName("centrePoint") var centrePoint: ArrayList<Double> = arrayListOf(),
    @SerializedName("stopPoints") var stopPoints: ArrayList<StopPointItem> = arrayListOf(),
    @SerializedName("pageSize") var pageSize: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("page") var page: Int? = null

)