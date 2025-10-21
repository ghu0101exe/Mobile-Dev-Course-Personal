package com.example.quanlythuvien.data

data class Student(
    val id: String,
    val tenSV: String,
    val sachDaMuon: MutableList<Book> = mutableListOf()
)