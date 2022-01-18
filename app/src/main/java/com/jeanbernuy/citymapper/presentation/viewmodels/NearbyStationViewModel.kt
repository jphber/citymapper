package com.jeanbernuy.citymapper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jeanbernuy.citymapper.core.AppConstants
import com.jeanbernuy.citymapper.core.Resource
import com.jeanbernuy.citymapper.domain.StopPointRepository
import kotlinx.coroutines.Dispatchers

class NearbyStationViewModel(private val repository: StopPointRepository) : ViewModel() {

    val fetchAllStopPoints = liveData(Dispatchers.IO) {
        try {
            emit(
                repository.fetchStopPoints(
                    AppConstants.LATITUDE,
                    AppConstants.LONGITUDE,
                    AppConstants.STOP_TYPES,
                    AppConstants.RADIUS
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    val fetchArrivalPredictions = liveData(Dispatchers.IO) {
        try {
            emit(
                repository.fetchListArrivalPredictions("940GZZLUGPK")
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}