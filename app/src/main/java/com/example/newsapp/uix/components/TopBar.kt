package com.example.newsapp.uix.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    text : String
){
    TopAppBar(
        title = {
            Row {
                Icon(painter = painterResource(id = R.drawable.hamburger), contentDescription = "", modifier = Modifier.size(24.dp,28.dp), tint = Color.White)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = text, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1b1a1f)),

        )
}