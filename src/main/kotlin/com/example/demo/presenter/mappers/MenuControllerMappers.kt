package com.example.demo.presenter.mappers

import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.presenter.api_models.menu_items.MenuItemsRequestAndResponseModel
import com.example.demo.presenter.api_models.menu_items_categories.MenuItemsCategoriesRequestAndResponse

fun List<MenuItemModel>.toMenuItemsRequestAndResponse(): MenuItemsRequestAndResponseModel =
        MenuItemsRequestAndResponseModel(this)

fun List<MenuItemCategoryModel>.toCategoriesRequestAndResponse(): MenuItemsCategoriesRequestAndResponse =
        MenuItemsCategoriesRequestAndResponse(this)
