package com.example.demo.domain.usecases

import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.services.MenuItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateOrUpdateMenuItemsUseCase @Autowired constructor(
        private val menuItemsService: MenuItemsService
) {
    fun invoke(menuItemModelsList: List<MenuItemModel>) {
        menuItemsService.createOrUpdateItems(menuItemModelsList)
    }
}