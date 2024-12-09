package com.example.newsapp.repository

import com.example.newsapp.datasource.HaberlerDataSource
import com.example.newsapp.data.entity.HaberlerItem

class HaberlerRepository(var hds: HaberlerDataSource) {

    // `List<HaberlerItem>` tipi kullanılıyor çünkü API'den dönen veri bu tipte
    fun getNews(country: String, tag: String, callback: (List<HaberlerItem>) -> Unit, errorCallback: (String) -> Unit) {
        hds.fetchNews(country, tag, callback, errorCallback)
    }

    fun getNewsfromRSS(rssUrl : String, callback: (List<HaberlerItem>) -> Unit, errorCallback: (String) -> Unit) {
        hds.fetchRSS(rssUrl, callback, errorCallback)
    }
}
