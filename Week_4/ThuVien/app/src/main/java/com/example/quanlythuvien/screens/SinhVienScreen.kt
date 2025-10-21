package com.example.quanlythuvien.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quanlythuvien.data.Student
import com.example.quanlythuvien.viewmodel.SharedViewModel

@Composable
fun SinhVienScreen(
    viewModel: SharedViewModel,
    onStudentSelected: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Chọn một sinh viên", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(uiState.allStudents, key = { it.id }) { student ->
                StudentItem(student = student) {
                    viewModel.selectStudent(student)
                    onStudentSelected() // Gọi hàm để quay về
                }
                Divider()
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onClick: () -> Unit) {
    Text(
        text = student.tenSV,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    )
}