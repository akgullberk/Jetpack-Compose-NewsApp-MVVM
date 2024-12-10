package com.example.newsapp.uix.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.uix.components.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Rss(navController: NavController){
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        ItemData(R.drawable.indir, "Cumhuriyet"),
        ItemData(R.drawable.sabah, "Sabah"),
        ItemData(R.drawable.karar, "Karar"),
        ItemData(R.drawable.haberturk, "HaberTürk"),
        ItemData(R.drawable.mynet, "Mynet"),
        ItemData(R.drawable.donanimhaber, "Donanım Haber"),
        ItemData(R.drawable.webrazzi, "Webrazii"),
        ItemData(R.drawable.chip, "Chip Online"),
        ItemData(R.drawable.kizlar, "Kızlar Soruyor"),
        ItemData(R.drawable.oksijen, "Gazete Oksijen"),
        ItemData(R.drawable.webtekno, "Webtekno"),
        ItemData(R.drawable.anadolu, "Anadolu Ajansı")


    )

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
            BottomBar(selectedItem = 1, navController = navController)
        }
    ){
        paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 2 sütunlu bir ızgara
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.size) { index ->
                GridItem(data = items[index],navController)
            }
        }
    }
}

@Composable
fun GridItem(data: ItemData,navController: NavController) {
    Box(modifier = Modifier.clickable { navController.navigate("haberrss") }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Card {
                    Image(
                        painter = painterResource(id = data.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(110.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = data.text,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)

                    )

            }

    }


}

data class ItemData(val imageRes: Int, val text: String)

