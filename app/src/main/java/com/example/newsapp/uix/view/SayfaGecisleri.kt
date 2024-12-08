package com.example.newsapp.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()
NavHost(navController = navController, startDestination = "anasayfa") {

    composable("anasayfa"){
        Anasayfa()
    }
    
}
}