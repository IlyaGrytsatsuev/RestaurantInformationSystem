package com.example.demo.presenter.api_models.menu_items_categories

import com.example.demo.domain.models.MenuItemCategoryModel

data class MenuItemsCategoriesRequestAndResponse(
        val categoriesList: List<MenuItemCategoryModel>
)
