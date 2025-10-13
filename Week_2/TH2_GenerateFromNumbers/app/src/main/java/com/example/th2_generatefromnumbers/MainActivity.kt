package com.example.thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thuchanh2.ui.theme.TH2_GenerateFromNumbers

// ====================================================================
// 1. MAIN ACTIVITY
// ====================================================================

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TH2_GenerateFromNumbers{
                NumberInputScreen()
            }
        }
    }
}

// ====================================================================
// 2. MÀN HÌNH CHÍNH (COMPOSABLE)
// ====================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberInputScreen() {
    var inputNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var generatedNumbers by remember { mutableStateOf(emptyList<Int>()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Number", fontWeight = FontWeight.SemiBold) })
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Thực hành 02",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
            )

            // KHỐI NHẬP LIỆU VÀ NÚT TẠO
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ô Nhập Liệu (TextField)
                OutlinedTextField(
                    value = inputNumber,
                    onValueChange = { newValue ->
                        // *** THAY ĐỔI TẠI ĐÂY: CHO PHÉP NHẬP BẤT KỲ KÝ TỰ NÀO ***
                        inputNumber = newValue

                        // Reset lỗi và danh sách cũ khi người dùng bắt đầu nhập lại
                        if (errorMessage != null || generatedNumbers.isNotEmpty()) {
                            errorMessage = null
                            generatedNumbers = emptyList()
                        }
                    },
                    label = { Text("Nhập vào số lượng") },
                    // Giữ KeyboardType.Number để ưu tiên bàn phím số, nhưng vẫn cho phép nhập chữ
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Nút Tạo
                Button(
                    onClick = {
                        // 1. Xử lý logic khi nhấn nút "Tạo"

                        // Cố gắng chuyển đổi sang Số nguyên.
                        // Nếu nhập "a", toIntOrNull() sẽ trả về null.
                        val num = inputNumber.toIntOrNull()

                        if (num != null && num > 0) {
                            // Đầu vào HỢP LỆ (Số nguyên dương)
                            errorMessage = null
                            val newList = (1..num).toList()
                            generatedNumbers = newList

                        } else {
                            //4Đầu vào KHÔNG HỢP LỆ (text, số âm, hoặc 0)
                            errorMessage = "Dữ liệu bạn nhập không hợp lệ"
                            generatedNumbers = emptyList() // Ẩn danh sách
                        }
                    },
                    modifier = Modifier.height(56.dp)
                ) {
                    Text("Tạo")
                }
            }

            // HIỂN THỊ THÔNG BÁO LỖI
            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 8.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
            }

            // DANH SÁCH CÁC NÚT (LazyColumn)
            if (generatedNumbers.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(generatedNumbers) { number ->
                        Button(
                            onClick = { /* Xử lý khi nhấn vào nút số */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text(
                                text = number.toString(),
                                fontSize = MaterialTheme.typography.titleLarge.fontSize
                            )
                        }
                    }
                }
            }
        }
    }
}

// ====================================================================
// 3. PREVIEW (Tùy chọn)
// ====================================================================

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Thuchanh2Theme {
        NumberInputScreen()
    }
}