package com.example.demo.domain.usecases.menu_items

import com.example.demo.domain.services.MenuItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteMenuItemUseCase @Autowired constructor(
        private val menuItemsService: MenuItemsService
) {
    operator fun invoke(ids: List<Int>){
        menuItemsService.deleteMenuItems(ids)
    }
}