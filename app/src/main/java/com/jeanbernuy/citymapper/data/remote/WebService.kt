package com.jeanbernuy.citymapper.data.remote

import com.jeanbernuy.citymapper.data.model.StopPoint
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("StopPoint/")
    suspend fun getStopPoints(
        @Query(value = "lat") latitude: Number,
        @Query(value = "lon") longitude: Number,
        @Query(value = "stopTypes") stopTypes: String,
        @Query(value = "radius") radius: Integer,
    ): StopPoint
}