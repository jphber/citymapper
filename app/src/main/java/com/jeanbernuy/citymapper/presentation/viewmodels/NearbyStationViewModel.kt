package com.jeanbernuy.citymapper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jeanbernuy.citymapper.core.AppConstants
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.domain.StopPointRepository
import kotlinx.coroutines.Dispatchers

class NearbyStationViewModel(private val repository: StopPointRepository) : ViewModel() {

    fun fetchAllStopPoints(latitude:Double, longitude:Double, stopTypes:String, radius: Int) = liveData(Dispatchers.IO) {
        try {
            emit(repository.fetchStopPoints(latitude, longitude, stopTypes, radius))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchArrivalPredictions(naptanId: String) = liveData(Dispatchers.IO) {
        try {
            emit(repository.fetchListArrivalPredictions(naptanId))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchAllValidRoutes(stationName: String, direction: String) = liveData(Dispatchers.IO) {
        try {
            emit(repository.fetchAllValidRoutes(stationName, direction))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}