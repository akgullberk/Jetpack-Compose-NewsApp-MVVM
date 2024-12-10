package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.uix.view.SayfaGecisleri
import com.example.newsapp.uix.viewmodel.HaberRSSViewModel
import com.example.newsapp.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anasayfaViewModel: AnasayfaViewModel by viewModels()
    val rssViewModel: HaberRSSViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                SayfaGecisleri(anasayfaViewModel,rssViewModel)

            }

        }
    }
}

