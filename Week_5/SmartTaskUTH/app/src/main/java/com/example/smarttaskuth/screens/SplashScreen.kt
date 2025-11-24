package com.example.smarttaskuth.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttaskuth.R
import com.example.smarttaskuth.navigation.Screen
// !! LƯU Ý: Code này sẽ được cập nhật ở Phần 3 để kiểm tra đăng nhập
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        delay(3000L) // Delay 3 giây

        // Logic này sẽ được thay đổi ở Phần 3
        val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val hasSeenOnboarding = sharedPref.getBoolean("hasSeenOnboarding", false)

        val destination = if (hasSeenOnboarding) {
            Screen.Login.route
        } else {
            Screen.Onboarding.route
        }

        navController.navigate(destination) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "UTH SmartTasks",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5)
            )
        }
    }
}