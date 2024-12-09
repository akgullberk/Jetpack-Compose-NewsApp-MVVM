package com.example.newsapp.retrofit

import com.example.newsapp.data.entity.Haberler
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HaberlerDao {
    @GET("news/getNews")
    fun getNews(
        @Query("country") country: String,
        @Query("tag") tag: String
    ): Call<Haberler>

    @GET("news/getNewsfromRSS")
    fun getNewsfromRSS(
        @Query("data.rss_url") rssUrl : String
    ): Call<Haberler>
}