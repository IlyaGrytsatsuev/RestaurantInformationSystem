package com.example.demo.domain.usecases.menu_items_categories

import com.example.demo.domain.services.MenuItemsCategoriesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteMenuItemCategoriesUseCase @Autowired constructor(
        private val menuItemsCategoriesService: MenuItemsCategoriesService
){
    operator fun invoke(ids: List<Int>){
        menuItemsCategoriesService.deleteMenuItemCategories(ids)
    }

}