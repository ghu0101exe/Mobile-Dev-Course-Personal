package com.example.quanlythuvien.viewmodel

import androidx.lifecycle.ViewModel
import com.example.quanlythuvien.data.Book
import com.example.quanlythuvien.data.DataManager
import com.example.quanlythuvien.data.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    init {
        // Khởi tạo với sinh viên đầu tiên
        if (DataManager.danhSachSinhVien.isNotEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedStudent = DataManager.danhSachSinhVien[0],
                    allStudents = DataManager.danhSachSinhVien,
                    allBooks = DataManager.danhSachSach
                )
            }
        }
    }

    fun selectStudent(student: Student) {
        _uiState.update { it.copy(selectedStudent = student) }
    }

    // --- HÀM NÀY ĐÃ ĐƯỢC SỬA LẠI ---
    fun borrowBook(book: Book) {
        val currentStudent = _uiState.value.selectedStudent
        if (currentStudent != null) {
            // Kiểm tra xem sinh viên đã mượn sách này chưa
            if (!currentStudent.sachDaMuon.any { it.id == book.id }) {

                // 1. Tạo ra một DANH SÁCH MỚI bằng cách thêm sách vào list cũ
                val newSachDaMuon = currentStudent.sachDaMuon + book

                // 2. Tạo một đối tượng Student MỚI với danh sách MỚI
                val updatedStudent = currentStudent.copy(
                    sachDaMuon = newSachDaMuon.toMutableList()
                )

                // 3. Cập nhật state với đối tượng Student MỚI
                _uiState.update {
                    it.copy(selectedStudent = updatedStudent)
                }
            }
        }
    }

    // --- HÀM NÀY ĐÃ ĐƯỢC SỬA LẠI ---
    fun returnBook(book: Book) {
        val currentStudent = _uiState.value.selectedStudent
        if (currentStudent != null) {

            // 1. Tạo ra một DANH SÁCH MỚI bằng cách lọc bỏ cuốn sách
            val newSachDaMuon = currentStudent.sachDaMuon.filterNot { it.id == book.id }

            // 2. Tạo một đối tượng Student MỚI với danh sách MỚI
            val updatedStudent = currentStudent.copy(
                sachDaMuon = newSachDaMuon.toMutableList()
            )

            // 3. Cập nhật state với đối tượng Student MỚI
            _uiState.update {
                it.copy(selectedStudent = updatedStudent)
            }
        }
    }
}

// Data class để giữ toàn bộ trạng thái của UI
data class LibraryUiState(
    val selectedStudent: Student? = null,
    val allStudents: List<Student> = emptyList(),
    val allBooks: List<Book> = emptyList()
)