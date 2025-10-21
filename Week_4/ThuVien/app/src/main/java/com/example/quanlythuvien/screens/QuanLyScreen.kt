// screens/QuanLyScreen.kt
package com.example.quanlythuvien.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
// KHÔNG CẦN import Icon nữa
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quanlythuvien.data.Book
import com.example.quanlythuvien.viewmodel.SharedViewModel

@Composable
fun QuanLyScreen(
    viewModel: SharedViewModel,
    onNavigateToStudents: () -> Unit,
    onNavigateToBooks: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedStudent = uiState.selectedStudent

    Scaffold(
        bottomBar = {
            Button(
                onClick = onNavigateToBooks,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Thêm sách")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Sinh viên", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = selectedStudent?.tenSV ?: "Chưa chọn sinh viên",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onNavigateToStudents) {
                    Text("Thay đổi")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Danh sách sách", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Card(modifier = Modifier.fillMaxWidth().weight(1f)) {
                if (selectedStudent != null && selectedStudent.sachDaMuon.isNotEmpty()) {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        items(selectedStudent.sachDaMuon, key = { it.id }) { book ->
                            BookItemChecked(
                                book = book,
                                onReturnClick = {
                                    // Gọi ViewModel để trả sách
                                    viewModel.returnBook(book)
                                }
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Bạn chưa mượn quyển sách nào.\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

// --- HÀM NÀY ĐÃ ĐƯỢC CẬP NHẬT ---
@Composable
fun BookItemChecked(book: Book, onReturnClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Khi người dùng nhấn vào Checkbox (đang được tick)
        // onCheckedChange sẽ được gọi với giá trị mới là 'false'
        Checkbox(
            checked = true, // Luôn hiển thị là đã tick
            onCheckedChange = { _ ->
                // Kích hoạt hành động trả sách
                onReturnClick()
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = book.tenSach,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f) // Cho Text chiếm hết không gian
        )
        // Đã xóa IconButton (nút X)
    }
}