package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.entity.HaberlerItem
import com.example.newsapp.repository.HaberlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var hrepo: HaberlerRepository) : ViewModel() {

    private val _newsList = MutableLiveData<List<HaberlerItem>>()
    val newsList: LiveData<List<HaberlerItem>> get() = _newsList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchNews(country: String, tag: String) {
        hrepo.getNews(country, tag, { haberlerItems ->
            // Haberler başarıyla alındığında
            _newsList.postValue(haberlerItems)
        }, { error ->
            // Hata mesajı alındığında
            _errorMessage.postValue(error)
        })
    }
}
