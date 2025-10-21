// Nằm trong: ui/components/CommonUI.kt
package com.example.resetpassword.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resetpassword.R // Đảm bảo bạn đã import R

// BIẾN BỊ LỖI CỦA BẠN NẰM Ở ĐÂY
val brandColor = Color(0xFF0A75E0) // Màu xanh dương (mình đổi màu 1 chút cho giống)

@Composable
fun LogoHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo), // Sử dụng logo.png
            contentDescription = "UTH Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "SmartTasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = brandColor // Sử dụng biến brandColor
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun StandardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun StandardButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = brandColor)
    ) {
        Text(text.uppercase(), fontSize = 16.sp)
    }
}