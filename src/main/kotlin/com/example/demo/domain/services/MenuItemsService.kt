package com.example.demo.domain.services

import com.example.demo.domain.models.MenuItemModel

interface MenuItemsService {

    fun getMenuItemsList(): List<MenuItemModel>
    fun createOrUpdateItems(items: List<MenuItemModel>)


}