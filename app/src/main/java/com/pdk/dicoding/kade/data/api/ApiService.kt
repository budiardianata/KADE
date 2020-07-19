package com.pdk.dicoding.kade.data.api

import android.app.Application
import com.pdk.dicoding.kade.BuildConfig
import com.pdk.dicoding.kade.BuildConfig.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Budi Ardianata on 15/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class ApiService(application: Application) {
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .cache(
                Cache(
                    application.cacheDir, (5 * 1024 * 1024).toLong()
                )
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val footballApi: FootballApi by lazy {
        retrofit.build().create(FootballApi::class.java)
    }
}