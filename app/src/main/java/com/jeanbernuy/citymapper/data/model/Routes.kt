package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class Routes(

    @SerializedName("lineId"             ) var lineId             : String?                       = null,
    @SerializedName("lineName"           ) var lineName           : String?                       = null,
    @SerializedName("direction"          ) var direction          : String?                       = null,
    @SerializedName("stopPointSequences" ) var stopPointSequences : ArrayList<StopPointSequence> = arrayListOf(),

)