package com.kot.retro.simplified

import android.app.Application
import com.kot.retro.retrofitsimplified.KRetrofitApi
import com.kot.retro.retrofitsimplified.interceptors.ConnectivityInterceptor
import okhttp3.OkHttpClient

class App : Application(){
   companion object{
       private const val API_BASE_URL: String = "http://musicstudio.zoobidev.com/api/v2/"
   }

    override fun onCreate() {
        super.onCreate()
        KRetrofitApi.initialize(API_BASE_URL , OkHttpClient.Builder().
            addInterceptor(ConnectivityInterceptor(this)).build())

    }
}