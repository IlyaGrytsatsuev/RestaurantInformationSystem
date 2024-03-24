package com.example.demo.presenter.api_models.menu_items

import com.example.demo.domain.models.MenuItemModel

internal data class MenuItemsRequestAndResponseModel(
    val menuItemsList: List<MenuItemModel>
)
