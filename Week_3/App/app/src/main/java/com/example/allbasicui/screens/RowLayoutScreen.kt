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
fun RowLayoutScreen(navController: NavController) {
    Scaffold(
        topBar = { GeneralTopAppBar(title = "Row Layout", navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Creating shades of blue programmatically
            val colors = listOf(
                listOf(Color(0xFFC5CAE9), Color(0xFF3F51B5), Color(0xFFC5CAE9)),
                listOf(Color(0xFFBBDEFB), Color(0xFF2196F3), Color(0xFFBBDEFB)),
                listOf(Color(0xFFB3E5FC), Color(0xFF03A9F4), Color(0xFFB3E5FC)),
                listOf(Color(0xFFB2EBF2), Color(0xFF00BCD4), Color(0xFFB2EBF2))
            )

            colors.forEach { rowColors ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowColors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 60.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color)
                        )
                    }
                }
            }
        }
    }
}