package com.example.th2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NumberListScreen() }
    }
}

@Composable
fun NumberListScreen() {
    var input by remember { mutableStateOf("") }
    var numbers by remember { mutableStateOf<List<Int>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    // Chỉ nhận số nguyên dương 1..n (loại bỏ mọi chữ/ký tự khác)
    fun parsePositiveIntOrNull(s: String): Int? {
        val t = s.trim()
        if (t.isEmpty()) return null
        if (!t.matches(Regex("""\d+"""))) return null   // loại +/-, ., e, chữ, kí tự
        return try {
            val n = t.toInt()
            if (n > 0) n else null
        } catch (_: NumberFormatException) { null }
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(32.dp))
                Text("Thực hành 02", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = input,
                        onValueChange = { input = it; error = null },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        singleLine = true,
                        placeholder = { Text("Nhập vào số lượng") },
                        isError = error != null,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = {
                            if (error != null)
                                Text(error!!, color = MaterialTheme.colorScheme.error)
                            else
                                Text("Chỉ nhập số nguyên dương (vd: 5)")
                        }
                    )
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = {
                            val n = parsePositiveIntOrNull(input)
                            if (n == null) {
                                error = "Dữ liệu bạn nhập không hợp lệ"
                                numbers = emptyList()
                            } else {
                                numbers = (1..minOf(n, 500)).toList() // chặn quá lớn
                                error = null
                            }
                        },
                        modifier = Modifier.height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Tạo", fontSize = 16.sp) }
                }

                Spacer(Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(numbers) { num ->
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE53935),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = num.toString(),
                                modifier = Modifier
                                    .padding(vertical = 6.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
