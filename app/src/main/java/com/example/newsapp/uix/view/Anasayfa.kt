package com.example.newsapp.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.entity.HaberlerItem
import com.example.newsapp.viewmodel.AnasayfaViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Anasayfa(anasayfaViewModel: AnasayfaViewModel){
    var selectedItem by remember { mutableStateOf(0) }

    // Observe the news list and error message from the ViewModel
    val newsList by anasayfaViewModel.newsList.observeAsState(emptyList())
    val errorMessage by anasayfaViewModel.errorMessage.observeAsState("")

    // Fetch news if it's not already loaded
    if (newsList.isEmpty()) {
        anasayfaViewModel.fetchNews(country = "tr", tag = "general") // Example parameters
    }

    Scaffold(
        containerColor = Color(0xFF1b1a1f),
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF0b0e13)
            ) {
                BottomNavigation(
                    backgroundColor = Color(0xFF0b0e13)
                ){
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home",tint = Color.White) },

                        selected = selectedItem == 0,
                        onClick = { selectedItem = 0 }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites",tint = Color.White) },

                        selected = selectedItem == 1,
                        onClick = { selectedItem = 1 }
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) },

                        selected = selectedItem == 2,
                        onClick = { selectedItem = 2 }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            // Show error message if available
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            // Display the grid of news items
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 sütunlu grid
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(newsList) { newsItem ->
                    NewsItem(newsItem) // Custom composable to display each news item
                }
            }
        }
    }
}

@Composable
fun NewsItem(haberlerItem: HaberlerItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(8.dp)
    ) {
        // Display the image using Landscapist-Glide
        GlideImage(
            imageModel = haberlerItem.image, // Replace with your image URL field
            contentDescription = "News Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = haberlerItem.name,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}