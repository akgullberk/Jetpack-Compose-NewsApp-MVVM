package com.example.newsapp.retrofit

import ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient(baseUrl:String) : Retrofit {
            // OkHttpClient'e interceptor ekleniyor
            val client = OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor()) // ApiKeyInterceptor burada ekleniyor
                .build()


            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client) // Interceptor'ü içeren OkHttpClient kullanılıyor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}