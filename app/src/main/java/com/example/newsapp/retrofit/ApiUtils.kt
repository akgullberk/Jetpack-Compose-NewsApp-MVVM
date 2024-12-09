package com.example.newsapp.retrofit

import com.example.newsapp.datasource.HaberlerDataSource
import com.example.newsapp.repository.HaberlerRepository

class ApiUtils {
    companion object{
        val BASE_URL = "https://api.collectapi.com/"

        fun getHaberlerDao() : HaberlerDao{
            return RetrofitClient.getClient(BASE_URL).create(HaberlerDao::class.java)
        }

    }
}