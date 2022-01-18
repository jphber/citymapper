package com.jeanbernuy.citymapper.data

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.core.RestEngine
import com.jeanbernuy.citymapper.data.model.Arrivals
import com.jeanbernuy.citymapper.data.model.StopPoint

class DataSource {

    suspend fun fetchAllStopPoints(
        latitude: Double,
        longitude: Double,
        stopTypes: String,
        radius: Int
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

    suspend fun fetchArrivalsPredictions(id: String): Resource<Arrivals>{
        return Resource.Success(RestEngine.restEngine.getListArrivalPredictions(id))
    }
}