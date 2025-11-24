package com.example.smarttaskuth.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.smarttaskuth.AuthViewModel
import com.example.smarttaskuth.R
import com.example.smarttaskuth.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity
    val signInState by authViewModel.signInState.collectAsState()

    // 1. Tạo Google Sign-In Launcher
    val googleSignInLauncher = authViewModel.getGoogleSignInLauncher(activity) { success, message ->
        if (success) {
            Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Sign In Failed: $message", Toast.LENGTH_LONG).show()
        }
    }

    // 2. Theo dõi trạng thái đăng nhập
    LaunchedEffect(signInState.isSuccess) {
        if (signInState.isSuccess) {
            navController.navigate(Screen.Profile.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        // Giao diện (giữ nguyên phần lớn)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_background), // <-- ĐÃ KHÔI PHỤC
                contentDescription = "Login Background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(76.dp))
            Text(text = "SmartTasks", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3F51B5))
            Text(text = "A simple and efficient to-do app", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Welcome\nReady to explore? Log in to get started.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // 3. Cập nhật Nút Đăng nhập
            Button(
                onClick = {
                    if (!signInState.isLoading) {
                        authViewModel.signInWithGoogle(context, googleSignInLauncher)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(50.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                enabled = !signInState.isLoading
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google), // <-- Đảm bảo đã thêm
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "SIGN IN WITH GOOGLE",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
        }

        // Logo Card
        Card(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 140.dp)
                .size(120.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        // Copyright
        Text(
            text = "© UTHSmartTasks",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )

        // 4. Hiển thị Loading
        if (signInState.isLoading) {
            CircularProgressIndicator()
        }
    }
}