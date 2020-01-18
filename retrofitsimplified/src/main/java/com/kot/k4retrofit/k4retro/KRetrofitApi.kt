package com.kot.k4retrofit.k4retro

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object KRetrofitApi {
    lateinit var retrofit: Retrofit
    fun initialize(BASE_URL: String, client: OkHttpClient) = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .setLenient()
                            .create()
                    )
            )
        client(client)
    }.build().also {
        retrofit = it
    }
}