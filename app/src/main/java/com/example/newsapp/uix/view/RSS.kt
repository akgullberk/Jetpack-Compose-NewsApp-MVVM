package com.example.newsapp.uix.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.uix.viewmodel.RSSViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Rss(navController: NavController){
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
    containerColor = Color(0xFF1b1a1f),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Icon(painter = painterResource(id = R.drawable.hamburger), contentDescription = "", modifier = Modifier.size(24.dp,28.dp), tint = Color.White)
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(text = "KAYNAKLAR", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1b1a1f)),

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
                    icon = { Icon(
                        Icons.Default.Home, contentDescription = "Home",tint = Color.White,
                        modifier = Modifier.size(32.dp,32.dp)) },

                    selected = selectedItem == 0,
                    onClick = { navController.navigate("anasayfa") }
                )
                BottomNavigationItem(
                    icon = { Icon(
                        Icons.Default.Favorite, contentDescription = "Favorites",tint = Color.White,
                        modifier = Modifier.size(32.dp,32.dp)) },

                    selected = selectedItem == 1,
                    onClick = {  }
                )
                BottomNavigationItem(
                    icon = { Icon(
                        Icons.Default.Settings, contentDescription = "Settings", tint = Color.White,
                        modifier = Modifier.size(32.dp,32.dp)) },

                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 }
                )
            }
        }
    }
    ){
        paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Image(painter = painterResource(id = R.drawable.hamburger), contentDescription ="", modifier = Modifier.size(100.dp,100.dp) )
                    Text(text = "dsadsadsa")
                }
            }
        }
    }
}


