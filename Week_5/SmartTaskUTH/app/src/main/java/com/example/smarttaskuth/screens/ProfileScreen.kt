package com.example.smarttaskuth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.CameraAlt // <-- Đảm bảo đã thêm thư viện icons-extended
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.smarttaskuth.AuthViewModel
import com.example.smarttaskuth.R
import com.example.smarttaskuth.navigation.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val currentUser = Firebase.auth.currentUser // Lấy người dùng hiện tại

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    // Không cần nút back
                },
                actions = {
                    // Nút Đăng xuất
                    IconButton(onClick = {
                        authViewModel.signOut(context) {
                            // Quay về Login sau khi đăng xuất
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Profile.route) { inclusive = true }
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Sign Out"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box {
                // Tải ảnh từ Google
                AsyncImage(
                    model = currentUser?.photoUrl ?: R.drawable.ic_default_profile, // <-- Đảm bảo đã thêm
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                // Nút camera nhỏ
                Icon(
                    imageVector = Icons.Default.CameraAlt, // <-- ĐÃ SỬA
                    contentDescription = "Edit Photo",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF3F51B5))
                        .padding(6.dp)
                        .align(Alignment.BottomEnd)
                )
            }


            Spacer(modifier = Modifier.height(32.dp))

            // Hiển thị thông tin thật
            OutlinedTextField(
                value = currentUser?.displayName ?: "No Name",
                onValueChange = {},
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = currentUser?.email ?: "No Email",
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Google không cung cấp ngày sinh
            OutlinedTextField(
                value = "", // Để trống
                onValueChange = {},
                label = { Text("Date of Birth (Chưa có)") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false, // Vô hiệu hóa
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}