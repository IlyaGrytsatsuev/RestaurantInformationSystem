package com.example.demo.domain.models

data class MenuItemCategoryModel(
        val id: Int,
        val name: String,
        val menuItemsModelsList: List<MenuItemModel>
)