package com.example.newsapp.uix.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.entity.HaberlerItem
import com.example.newsapp.repository.HaberlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RSSViewModel @Inject constructor(var hrepo: HaberlerRepository) : ViewModel(){

    private val _newsList = MutableLiveData<List<HaberlerItem>>()
    val newsList: LiveData<List<HaberlerItem>> get() = _newsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchRSS(rssUrl : String) {
        hrepo.getNewsfromRSS(rssUrl, { haberlerItems ->
            // Haberler başarıyla alındığında
            _newsList.postValue(haberlerItems)
        }, { error ->
            // Hata mesajı alındığında
            _errorMessage.postValue(error)
        })
    }
}