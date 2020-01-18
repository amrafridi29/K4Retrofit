package com.kot.k4retrofit.simplified

import android.app.Application
import com.kot.k4retrofit.k4retro.KRetrofitApi
import com.kot.k4retrofit.k4retro.interceptors.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class App : Application(){
   companion object{
       private const val API_BASE_URL: String = "http://musicstudio.zoobidev.com/api/v2/"
   }

    override fun onCreate() {
        super.onCreate()
        KRetrofitApi.initialize(
            API_BASE_URL, OkHttpClient.Builder().
            addInterceptor(
                ConnectivityInterceptor(
                    this
                )
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build())
    }
}