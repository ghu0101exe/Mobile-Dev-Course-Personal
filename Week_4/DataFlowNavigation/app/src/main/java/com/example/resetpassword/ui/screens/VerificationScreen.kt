// ui/screens/VerificationScreen.kt
package com.example.resetpassword.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.resetpassword.ui.components.LogoHeader
import com.example.resetpassword.ui.components.StandardButton
import com.example.resetpassword.viewmodel.ResetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(
    viewModel: ResetViewModel,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verification") },
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
            Text("Verify Code", style = MaterialTheme.typography.headlineSmall)
            Text("Enter the code we just sent you on your registered Email")
            Spacer(modifier = Modifier.height(24.dp))

            // 1 ô duy nhất cho 5 số OTP
            OutlinedTextField(
                value = uiState.otp,
                onValueChange = { viewModel.onOtpChange(it) },
                label = { Text("Enter 5-digit code") },
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
            StandardButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = uiState.isOtpValid // Chỉ bật khi OTP = "11205"
            )
        }
    }
}