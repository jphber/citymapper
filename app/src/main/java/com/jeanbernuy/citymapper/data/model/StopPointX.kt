package com.jeanbernuy.citymapper.data.model

import com.google.gson.annotations.SerializedName

data class StopPointX(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
)