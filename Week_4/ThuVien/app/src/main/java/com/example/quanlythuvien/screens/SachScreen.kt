// screens/SachScreen.kt
package com.example.quanlythuvien.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quanlythuvien.data.Book
import com.example.quanlythuvien.viewmodel.SharedViewModel

@Composable
fun SachScreen(
    viewModel: SharedViewModel,
    onBookSelected: () -> Unit // Hàm để quay lại sau khi chọn sách
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Chọn sách để mượn", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(uiState.allBooks, key = { it.id }) { book ->
                SachItem(book = book) {
                    // Gọi hàm mượn sách từ ViewModel
                    viewModel.borrowBook(book)
                    // Hiển thị thông báo
                    Toast.makeText(context, "Đã mượn: ${book.tenSach}", Toast.LENGTH_SHORT).show()
                    // Quay lại màn hình Quản lý
                    onBookSelected()
                }
                Divider()
            }
        }
    }
}

@Composable
fun SachItem(book: Book, onClick: () -> Unit) {
    Text(
        text = book.tenSach,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    )
}