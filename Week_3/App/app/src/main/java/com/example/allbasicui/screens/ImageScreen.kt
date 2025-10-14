package com.example.allbasicui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.allbasicui.R
import com.example.allbasicui.components.GeneralTopAppBar

@Composable
fun ImageScreen(navController: NavController) {
    // URL cho ảnh đầu tiên
    val imageUrl = "https://tuyensinhhuongnghiep.vn/upload/images/2023/09/08/truong-dai-hoc-giao-thong-van-tai-tphcm.jpg"

    Scaffold(
        topBar = { GeneralTopAppBar(title = "Images", navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Thêm scroll nếu nội dung dài
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // Khoảng cách giữa các phần tử
        ) {
            // Ảnh đầu tiên từ URL sử dụng Coil
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image From URL",
                contentScale = ContentScale.Crop, // Điều chỉnh để ảnh hiển thị đẹp hơn
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Đặt chiều cao cố định
            )
            Text(text = imageUrl, textAlign = TextAlign.Center) // Hiển thị URL bên dưới ảnh

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa 2 ảnh

            // Ảnh thứ hai từ drawable cục bộ
            Image(
                painter = painterResource(id = R.drawable.in_app_image_second), // Bạn cần thêm ảnh này vào drawable
                contentDescription = "In-app Image",
                contentScale = ContentScale.Crop, // Điều chỉnh để ảnh hiển thị đẹp hơn
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Đặt chiều cao cố định
            )
            Text(text = "In app") // Văn bản mô tả ảnh cục bộ
        }
    }
}