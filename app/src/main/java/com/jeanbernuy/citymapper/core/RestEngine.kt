package com.jeanbernuy.citymapper.core

import com.google.gson.GsonBuilder
import com.jeanbernuy.citymapper.core.AppConstants.APP_KEY
import com.jeanbernuy.citymapper.core.AppConstants.BASE_URL
import com.jeanbernuy.citymapper.data.remote.WebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestEngine {

    val interceptor = HttpLoggingInterceptor()
    val logginInterceptor = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val httpClient: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().addHeader(
                    "app_key",
                    "$APP_KEY"
                ).build()
            )
        }.addInterceptor(logginInterceptor)
        .build()

    val restEngine by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(httpClient)
            .build().create(WebService::class.java)
    }

}
