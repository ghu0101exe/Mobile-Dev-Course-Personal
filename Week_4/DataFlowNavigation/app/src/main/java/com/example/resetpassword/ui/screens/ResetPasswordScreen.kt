// ui/screens/ResetPasswordScreen.kt
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
fun ResetPasswordScreen(
    viewModel: ResetViewModel,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reset Password") },
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
            Text("Create new password", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))

            StandardTextField(
                value = uiState.newPassword,
                onValueChange = { viewModel.onNewPasswordChange(it) },
                label = "Password",
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextField(
                value = uiState.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = "Confirm Password",
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(24.dp))
            StandardButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = uiState.doPasswordsMatch // Chỉ bật khi 2 pass trùng nhau
            )
        }
    }
}