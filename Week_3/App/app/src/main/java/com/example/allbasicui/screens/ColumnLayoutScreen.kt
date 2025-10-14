package com.example.allbasicui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allbasicui.components.GeneralTopAppBar

@Composable
fun ColumnLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = { GeneralTopAppBar(title = "Column Layout", navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val greenShades = listOf(Color(0xFFA5D6A7), Color(0xFF66BB6A), Color(0xFFA5D6A7))
            greenShades.forEach { color ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(color)
                )
            }
        }
    }
}