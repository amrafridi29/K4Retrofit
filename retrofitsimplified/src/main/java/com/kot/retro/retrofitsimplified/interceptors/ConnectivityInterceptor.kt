package com.kot.retro.retrofitsimplified.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.kot.retro.retrofitsimplified.exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()){
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    fun isConnected() : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return (netInfo!=null && netInfo.isConnected)
    }

}