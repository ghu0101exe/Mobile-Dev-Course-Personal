package com.example.allbasicui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allbasicui.R
import com.example.allbasicui.navigation.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Student Info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "Nguyễn Hà Gia Huy", fontWeight = FontWeight.Bold)
            Text(text = "056205005458")
        }

        Spacer(modifier = Modifier.weight(0.5f))

        // Main Content
        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Jetpack Compose",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.weight(1f))

        // Button
        Button(
            onClick = { navController.navigate(Routes.COMPONENT_LIST) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "I'm ready", fontSize = 16.sp)
        }
    }
}