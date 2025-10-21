// ui/screens/ForgotPasswordScreen.kt
package com.example.resetpassword.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.resetpassword.ui.components.LogoHeader
import com.example.resetpassword.ui.components.StandardButton
import com.example.resetpassword.ui.components.StandardTextField
import com.example.resetpassword.viewmodel.ResetViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ResetViewModel,
    onNextClicked: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoHeader()
            Text("Forget Password?", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            Text("Enter your Email, we will send you a verification code.")
            Spacer(modifier = Modifier.height(24.dp))

            StandardTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = "Your Email"
            )
            Spacer(modifier = Modifier.height(24.dp))
            StandardButton(
                text = "Next",
                onClick = onNextClicked,
                enabled = uiState.isEmailValid // Chỉ bật khi email đúng định dạng @gmail.com
            )
        }
    }
}