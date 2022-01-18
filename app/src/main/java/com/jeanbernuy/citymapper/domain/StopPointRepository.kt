package com.jeanbernuy.citymapper.domain

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.model.Arrivals
import com.jeanbernuy.citymapper.data.model.StopPoint

interface StopPointRepository {

    suspend fun fetchStopPoints(latitude: Double, longitude: Double, stopTypes: String, radius: Int): Resource<StopPoint>
    suspend fun fetchListArrivalPredictions(id: String): Resource<Arrivals>
}