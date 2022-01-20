package com.jeanbernuy.citymapper.core

import android.location.Location

object LocationHelper {

    private const val SIXTEEN_KILOMETERS = 16000

    fun isLondon(longitude: Double, latitude: Double) : Boolean {
        val results = FloatArray(1)
        Location.distanceBetween(AppConstants.LONDON_CENTER_LATITUDE, AppConstants.LONDON_CENTER_LONGITUDE, latitude, longitude, results)
        val distanceInMeters = results[0]
        return distanceInMeters < SIXTEEN_KILOMETERS
    }
}