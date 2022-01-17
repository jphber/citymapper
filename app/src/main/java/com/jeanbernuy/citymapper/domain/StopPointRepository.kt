package com.jeanbernuy.citymapper.domain

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.model.StopPoint

interface StopPointRepository {
    suspend fun fetchStopPoints(
        latitude: Double,
        longitude: Double,
        stopTypes: String,
        radius: Int
    ): Resource<StopPoint>
}