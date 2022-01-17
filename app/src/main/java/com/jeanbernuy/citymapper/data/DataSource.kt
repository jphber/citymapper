package com.jeanbernuy.citymapper.data

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.core.RestEngine
import com.jeanbernuy.citymapper.data.model.StopPoint

class DataSource {

    suspend fun fetchAllStopPoints(
        latitude: Double,
        longitude: Double,
        stopTypes: String,
        radius: Integer
    ): Resource<StopPoint> {
        return Resource.Success(
            RestEngine.restEngine.getStopPoints(
                latitude,
                longitude,
                stopTypes,
                radius
            )
        )
    }
}