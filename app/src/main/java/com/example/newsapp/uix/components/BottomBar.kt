package com.example.newsapp.uix.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R

@Composable
fun BottomBar(
    selectedItem: Int,
    navController: NavController
){



    BottomAppBar(
        containerColor = Color(0xFF0b0e13),
        modifier = Modifier.height(76.dp) // Yüksekliği ayarlayın
    ) {
        BottomNavigation(
            backgroundColor = Color(0xFF0b0e13)
        ){
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedItem == 0) Color.White else Color(0xFF8e99a2),
                        modifier = Modifier.size(32.dp,32.dp)
                    )
                },
                selected = selectedItem == 0,
                onClick = { navController.navigate("anasayfa") }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.article),
                        contentDescription = "",
                        tint = if (selectedItem == 1) Color.White else Color(0xFF8e99a2),
                        modifier = Modifier.size(32.dp,32.dp)
                    )
                },
                selected = selectedItem == 1,
                onClick = { navController.navigate("rss") }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Default.Person, contentDescription = "",tint = Color(0xFF8e99a2),
                        modifier = Modifier.size(32.dp,32.dp))
                },
                selected = selectedItem == 2,
                onClick = {  }
            )
        }
    }
}