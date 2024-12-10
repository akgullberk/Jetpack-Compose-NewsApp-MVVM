package com.example.newsapp.uix.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.data.entity.HaberlerItem
import com.example.newsapp.viewmodel.AnasayfaViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(anasayfaViewModel: AnasayfaViewModel,navController: NavController){
    var selectedCategory by remember { mutableStateOf("general") } // Default category
    var selectedItem by remember { mutableStateOf(0) }

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
            TopAppBar(
                title = {  },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1b1a1f)),
                actions = {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {

                        items(listOf("Tümü" to "general", "Spor" to "sport", "Ekonomi" to "economy", "Teknoloji" to "technology", "Magazin" to "magazine")) { (label, tag) ->
                            TextButton(onClick = { selectedCategory = tag }) {
                                Text(
                                    text = label,
                                    color = if (selectedCategory == tag) Color.White else Color(0xFF898c8a),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF0b0e13),
                modifier = Modifier.height(76.dp) // Yüksekliği ayarlayın
            ) {
                BottomNavigation(
                    backgroundColor = Color(0xFF0b0e13)
                ){
                    BottomNavigationItem(

                        icon = {

                            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFF8e99a2),
                                modifier = Modifier.size(32.dp,32.dp)) },

                        selected = selectedItem == 0,
                        onClick = {  }
                    )
                    BottomNavigationItem(
                        icon = {

                            Icon(Icons.Default.Search, contentDescription = "",tint = Color(0xFF8e99a2),
                                modifier = Modifier.size(32.dp,32.dp)) },

                        selected = selectedItem == 1,
                        onClick = { navController.navigate("rss") }
                    )
                    BottomNavigationItem(
                        icon = {

                            Icon(Icons.Default.Person, contentDescription = "",tint = Color(0xFF8e99a2),
                                modifier = Modifier.size(32.dp,32.dp)) },

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

@Composable
fun NewsItem(haberlerItem: HaberlerItem) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                // Tıklanınca URL'yi tarayıcıda aç
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(haberlerItem.url))
                context.startActivity(intent)
            }

    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF171e26))

        ) {
            Box {
                GlideImage(
                    imageModel = haberlerItem.image, // Replace with your image URL field
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }
            // Display the image using Landscapist-Glide




            Column(modifier = Modifier.padding(16.dp)) {


                    Text(
                        text = haberlerItem.source.uppercase(),
                        color = Color(0xFF9a9c9d),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,

                        )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = haberlerItem.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 25.sp
                    )

            }


        }
    }

}