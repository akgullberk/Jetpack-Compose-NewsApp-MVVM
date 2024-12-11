package com.example.newsapp.uix.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.uix.components.BottomBar
import com.example.newsapp.uix.components.TopBar
import com.example.newsapp.uix.viewmodel.HaberRSSViewModel
import com.example.newsapp.viewmodel.AnasayfaViewModel

@Composable
fun HaberRSS(text: String,navController: NavController,anasayfaViewModel: AnasayfaViewModel){
    var selectedCategory by remember { mutableStateOf("sport") } // Default category

    // Observe the news list and error message from the ViewModel
    val newsList by anasayfaViewModel.newsList.observeAsState(emptyList())
    val errorMessage by anasayfaViewModel.errorMessage.observeAsState("")

    // Fetch news whenever the selected category changes
    LaunchedEffect(selectedCategory) {
        anasayfaViewModel.fetchNews(country = "tr", tag = selectedCategory)
    }

    Scaffold(
        containerColor = Color(0xFF1b1a1f),
        topBar = {
            TopBar(text = text.uppercase())
        },
        bottomBar = {
            BottomBar(selectedItem = 1, navController = navController)
        }
        
    ) {paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Show error message if available
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            // Display the grid of news items
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2), // 2 sütunlu grid
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp // Her öğe arasındaki dikey boşluk
            ) {
                items(newsList) { newsItem ->
                    NewsItem(newsItem) // Custom composable to display each news item
                }
            }
        }

    }
}