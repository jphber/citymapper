package com.jeanbernuy.citymapper.data

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.core.RestEngine
import com.jeanbernuy.citymapper.data.model.Arrivals
import com.jeanbernuy.citymapper.data.model.Routes
import com.jeanbernuy.citymapper.data.model.StopPoint
import retrofit2.http.Path

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

    suspend fun fetchAllValidRoutes(stationName: String, direction: String): Resource<Routes>{
        return Resource.Success(RestEngine.restEngine.getAllValidRoutes(stationName, direction))
    }
}