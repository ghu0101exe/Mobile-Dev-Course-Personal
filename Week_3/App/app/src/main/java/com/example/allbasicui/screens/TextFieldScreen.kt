package com.example.allbasicui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allbasicui.components.GeneralTopAppBar

@Composable
fun TextFieldScreen(navController: NavController) {
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = { GeneralTopAppBar(title = "TextField", navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Trạng thái nhập") },
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "Tự động cập nhật dữ liệu theo textfield")
        }
    }
}