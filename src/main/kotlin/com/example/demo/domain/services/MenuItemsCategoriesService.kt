package com.example.demo.domain.services

import com.example.demo.domain.models.MenuItemCategoryModel

interface MenuItemsCategoriesService {

    fun getMenuItemsCategoriesList(): List<MenuItemCategoryModel>

    fun createOrUpdateMenuItemsCategories(
            menuItemsCategories: List<MenuItemCategoryModel>
    )
}