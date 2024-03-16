package com.example.demo.domain.usecases.menu_items

import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.services.MenuItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetMenuItemsUseCase @Autowired constructor(
        private val menuItemsService: MenuItemsService
) {
    operator fun invoke(): List<MenuItemModel> = menuItemsService.getMenuItemsList()
}