package com.example.quanlythuvien.data

object DataManager {
    val danhSachSach = mutableListOf(
        Book("S01", "Sách 01"),
        Book("S02", "Sách 02"),
        Book("S03", "Sách 03")
    )

    val danhSachSinhVien = mutableListOf(
        Student("SV001", "Nguyễn Văn A"),
        Student("SV002", "Nguyễn Thị B"),
        Student("SV003", "Nguyễn Văn C")
    )

    // Khởi tạo một vài dữ liệu mượn sách mẫu
    init {
        danhSachSinhVien.find { it.id == "SV001" }?.sachDaMuon?.add(danhSachSach[0])
        danhSachSinhVien.find { it.id == "SV001" }?.sachDaMuon?.add(danhSachSach[1])
        danhSachSinhVien.find { it.id == "SV002" }?.sachDaMuon?.add(danhSachSach[0])
    }
}