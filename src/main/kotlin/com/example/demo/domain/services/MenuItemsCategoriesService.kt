package com.example.demo.domain.services

import com.example.demo.domain.models.MenuItemCategoryModel

internal interface MenuItemsCategoriesService {

    fun getMenuItemsCategoriesList(): List<MenuItemCategoryModel>

    fun createOrUpdateMenuItemsCategories(
            menuItemsCategories: List<MenuItemCategoryModel>
    )
    fun deleteMenuItemCategories(ids: List<Int>)

}