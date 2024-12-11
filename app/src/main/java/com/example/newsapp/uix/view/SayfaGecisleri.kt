package com.example.newsapp.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.uix.viewmodel.HaberRSSViewModel
import com.example.newsapp.viewmodel.AnasayfaViewModel

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,haberRSSViewModel: HaberRSSViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {

    composable("anasayfa"){
        Anasayfa(anasayfaViewModel,navController)
    }

        composable("rss"){
            Rss(navController)
        }

        composable(
            "haberrss/{text}",
            arguments = listOf(navArgument("text"){
                type = NavType.StringType
            })

        ){ backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            HaberRSS(text,navController,anasayfaViewModel)
        }
    
}
}