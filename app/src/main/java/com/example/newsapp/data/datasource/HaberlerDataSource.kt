package com.example.newsapp.datasource

import android.util.Log
import com.example.newsapp.data.entity.Haberler
import com.example.newsapp.data.entity.HaberlerItem
import com.example.newsapp.retrofit.HaberlerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HaberlerDataSource(var hdao: HaberlerDao) {

    // API'den haberleri çekmek için method
    fun fetchNews(country: String, tag: String, callback: (List<HaberlerItem>) -> Unit, errorCallback: (String) -> Unit) {
        hdao.getNews(country, tag).enqueue(object : Callback<Haberler> {
            override fun onResponse(call: Call<Haberler>, response: Response<Haberler>) {
                if (response.isSuccessful) {
                    // response.body() içinde gelen veri, `Haberler` türünde olacak, bu yüzden
                    // `Haberler`'in `result` özelliğinden `List<HaberlerItem>` alınır.
                    response.body()?.result?.let {
                        callback(it) // HaberlerItem listesi döndürülüyor
                    } ?: errorCallback("No data found")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    errorCallback("Failed response code: ${response.code()}, message: $errorBody")
                    Log.e("API_ERROR", "Code: ${response.code()}, Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<Haberler>, t: Throwable) {
                errorCallback("Error: ${t.message}")

            }
        })
    }

    fun fetchRSS(rssUrl : String, callback: (List<HaberlerItem>) -> Unit, errorCallback: (String) -> Unit) {
        hdao.getNewsfromRSS(rssUrl).enqueue(object : Callback<Haberler> {
            override fun onResponse(call: Call<Haberler>, response: Response<Haberler>) {
                if (response.isSuccessful) {
                    // response.body() içinde gelen veri, `Haberler` türünde olacak, bu yüzden
                    // `Haberler`'in `result` özelliğinden `List<HaberlerItem>` alınır.
                    response.body()?.result?.let {
                        callback(it) // HaberlerItem listesi döndürülüyor
                    } ?: errorCallback("No data found")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    errorCallback("Failed response code: ${response.code()}, message: $errorBody")
                    Log.e("API_ERROR", "Code: ${response.code()}, Body: $errorBody")
                }
            }

            override fun onFailure(call: Call<Haberler>, t: Throwable) {
                errorCallback("Error: ${t.message}")

            }
        })
    }
}
