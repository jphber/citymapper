package com.jeanbernuy.citymapper.data.repository

import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.data.DataSource
import com.jeanbernuy.citymapper.data.model.Arrivals
import com.jeanbernuy.citymapper.data.model.Routes
import com.jeanbernuy.citymapper.data.model.StopPoint
import com.jeanbernuy.citymapper.domain.StopPointRepository

class StopPointDataRepository(private val dataSource: DataSource) : StopPointRepository {
    override suspend fun fetchStopPoints(
        latitude: Double, longitude: Double, stopTypes: String, radius: Int
    ): Resource<StopPoint> {
        return dataSource.fetchAllStopPoints(latitude, longitude, stopTypes, radius)
    }

    override suspend fun fetchListArrivalPredictions(id:String): Resource<Arrivals> {
       return dataSource.fetchArrivalsPredictions(id)
    }

    override suspend fun fetchAllValidRoutes(stationName: String, direction: String): Resource<Routes> {
        return dataSource.fetchAllValidRoutes(stationName, direction)
    }

}