package com.example.dataapi.data

import com.google.gson.annotations.SerializedName

// Data Model để lưu trữ thông tin sản phẩm
data class Product(
    // @SerializedName là bản đồ cho thư viện Gson để ánh xạ dữ liệu từ JSON sang đối tượng

    @SerializedName("id")
    val id: String, // API trả về Int, nhưng Gson sẽ tự ép kiểu sang String

    @SerializedName("title")
    val name: String,

    @SerializedName("price")
    val price: Double,

    // Mapping "desc" tu JSON vao property "description"
    @SerializedName("description")
    val description: String,

    // Mapping "imgURL" tu JSON vao property "image"
    @SerializedName("thumbnail")
    val image: String
)