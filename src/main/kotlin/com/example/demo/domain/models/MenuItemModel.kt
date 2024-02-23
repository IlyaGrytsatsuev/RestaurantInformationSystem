package com.example.demo.domain.models

data class MenuItemModel(
        val id: Int,
        val name: String,
        val categoryId: Int,
        val price: Int,
        val description: String,
        val imgPath: String,
)
