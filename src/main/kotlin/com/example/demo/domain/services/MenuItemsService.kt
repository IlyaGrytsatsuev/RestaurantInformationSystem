package com.example.demo.domain.services

import com.example.demo.domain.models.MenuItemModel
import org.springframework.stereotype.Service

internal interface MenuItemsService {

    fun getMenuItemsList(): List<MenuItemModel>
    fun createOrUpdateMenuItems(items: List<MenuItemModel>)
    fun deleteMenuItems(ids: List<Int>)

}