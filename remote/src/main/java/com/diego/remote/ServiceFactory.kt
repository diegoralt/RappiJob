package com.diego.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {

    companion object {
        @Volatile
        private var INSTANCE: Service? = null

        fun getInstance(baseUrl: String): Service {
            return INSTANCE ?: synchronized(this) {
                makeService(baseUrl).also {
                    INSTANCE = it
                }
            }
        }

        private fun makeService(baseUrl: String): Service =
            Retrofit.Builder()
                .client(getClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)

        private fun getClient(): OkHttpClient =
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(makeInterceptor()).build()

        private fun makeInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}