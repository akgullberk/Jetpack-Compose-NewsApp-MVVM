package com.example.newsapp.data.entity

data class Haberler(
    val success: Boolean,
    val result: List<HaberlerItem>
)

data class HaberlerItem(
    val key: String,
    val url: String,
    val description: String,
    val image: String,
    val name: String,
    val source: String
)