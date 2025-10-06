package com.example.homework_formcheckage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AgeFormScreen() }   // sẽ nhận sau khi có activity-compose
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeFormScreen() {
    var name by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }

    fun classify(age: Int): String = when {
        age < 2 -> "Em bé"
        age in 2..5 -> "Trẻ em"
        age in 6..65 -> "Người lớn"
        else -> "Người già"
    }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "THỰC HÀNH 01",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                    textAlign = TextAlign.Center
                )

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Họ và tên")
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            singleLine = true,
                            placeholder = { Text("Nhập họ tên") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Tuổi")
                        OutlinedTextField(
                            value = ageText,
                            onValueChange = {
                                ageText = it
                                ageError = null
                            },
                            singleLine = true,
                            placeholder = { Text("Nhập số tuổi") },
                            isError = ageError != null,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            supportingText = {
                                ageError?.let { msg -> Text(msg, color = MaterialTheme.colorScheme.error) }
                            }
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        val age = ageText.toIntOrNull()
                        if (age == null || age < 0) {
                            ageError = "Tuổi phải là số không âm"
                            result = null
                            return@Button
                        }
                        val label = classify(age)
                        result = if (name.isBlank()) "Kết quả: $label ($age tuổi)"
                        else "Kết quả: $name ($age) là $label"
                    },
                    modifier = Modifier.widthIn(min = 160.dp).height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) { Text("Kiểm tra", fontSize = 16.sp) }

                Spacer(Modifier.height(16.dp))

                result?.let {
                    Text(it, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
                }
            }
        }
    }
}
