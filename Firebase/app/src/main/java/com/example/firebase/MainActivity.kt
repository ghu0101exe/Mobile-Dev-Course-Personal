package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import vn.edu.ut.hieupm9898.firebaseauth.ui.theme.FirebaseAuthTheme

class MainActivity : ComponentActivity() {
    // Khởi tạo Firebase Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Khởi tạo Firebase Analytics
        firebaseAnalytics = Firebase.analytics

        setContent {
            FirebaseAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseAuthApp(firebaseAnalytics)
                }
            }
        }
    }
}

@Composable
fun FirebaseAuthApp(firebaseAnalytics: FirebaseAnalytics) {
    var loginResult by remember { mutableStateOf<LoginResult?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val success = (0..1).random() == 1
                loginResult = if (success) {
                    val email = "sample@gmail.com"

                    // Gửi event đăng nhập thành công lên Firebase
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
                        param(FirebaseAnalytics.Param.METHOD, "Gmail")
                        param("login_status", "success")
                        param("user_email", email)
                    }

                    LoginResult.Success(email)
                } else {
                    // Gửi event lỗi đăng nhập
                    firebaseAnalytics.logEvent("login_failed") {
                        param("reason", "User canceled the Google sign-in process")
                    }

                    LoginResult.Error("User canceled the Google sign-in process.")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2395f3),
                contentColor = Color.White
            )
        ) {
            Text(text = "Login by Gmail", fontSize = 22.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hiển thị thông báo lỗi hoặc thành công
        AnimatedVisibility(visible = loginResult != null) {
            when (val result = loginResult) {
                is LoginResult.Success -> SuccessMessage(result.email)
                is LoginResult.Error -> ErrorMessage(result.message)
                null -> {}
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFEAC7C4), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Google Sign-In Failed",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = message,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun SuccessMessage(email: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFB8DDF8), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Success!",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Hi $email\nWelcome to UTHSmartTasks",
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}

// Kết quả đăng nhập
sealed class LoginResult {
    data class Success(val email: String) : LoginResult()
    data class Error(val message: String) : LoginResult()
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseTheme {
        Greeting("Android")
    }
}