// ui/screens/ConfirmScreen.kt
package com.example.resetpassword.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.resetpassword.ui.components.LogoHeader
import com.example.resetpassword.ui.components.StandardButton
import com.example.resetpassword.ui.components.StandardTextField
import com.example.resetpassword.viewmodel.ResetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(
    viewModel: ResetViewModel,
    onSummitClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirm") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Text("Confirm", style = MaterialTheme.typography.headlineSmall)
            Text("We are here to help you!")
            Spacer(modifier = Modifier.height(24.dp))

            StandardTextField(
                value = uiState.email, // Lấy email từ ViewModel
                onValueChange = { },
                label = "uth@gmail.com",

                )
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextField(
                value = "123456", // Hardcode như trong ảnh
                onValueChange = { },
                label = "123456",

                )
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextField(
                value = uiState.newPassword, // Lấy pass mới từ ViewModel
                onValueChange = { },
                label = "Password",
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))
            StandardButton(
                text = "Summit", // Tên nút trong ảnh
                onClick = onSummitClicked
            )
        }
    }
}