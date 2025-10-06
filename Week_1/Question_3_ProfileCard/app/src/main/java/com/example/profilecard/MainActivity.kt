package com.example.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(Modifier.fillMaxSize(), color = Color.Red) {
                ProfileScreen(
                    name = "Huy Nguyen",
                    location = "Ho Chi Minh, VIE",
                    onBack = { this@MainActivity.finish() },
                    onEdit = { }
                )
            }
        }
    }
}


@Composable
fun ProfileScreen(
    name: String,
    location: String,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(50.dp)
        ) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }

        IconButton(
            onClick = onEdit,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(50.dp)
        ) { Icon(Icons.Filled.Edit, contentDescription = "Edit") }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Red, CircleShape)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = name,
                fontSize = 30.sp,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = location,
                textAlign = TextAlign.Right,
                fontSize = 20.sp,
                color = Color.Red
            )
        }
    }
}

