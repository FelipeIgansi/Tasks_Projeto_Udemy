package com.devmasterteam.tasks.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {

        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder().build()
            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl("http://devmasterteam.com/CursoAndroid API/")
                        .client(httpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

            }
            return INSTANCE
        }
        fun <G> getService(serviceClass: Class<G>): G{// G = Generic
            return getRetrofitInstance().create(serviceClass)
        }
    }
}