package com.example.newsapp.uix.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Anasayfa(){
    var selectedItem by remember { mutableStateOf(0) }
    Scaffold(
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
        Text(text = "dasdsada", modifier = Modifier.padding(paddingValues))
    }
}