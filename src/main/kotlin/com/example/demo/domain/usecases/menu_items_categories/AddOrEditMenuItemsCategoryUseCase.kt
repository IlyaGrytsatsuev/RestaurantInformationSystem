package com.example.demo.domain.usecases.menu_items_categories

import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.services.MenuItemsCategoriesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddOrEditMenuItemsCategoryUseCase @Autowired constructor(
        private val menuItemsCategoriesService: MenuItemsCategoriesService
){

    operator fun invoke(categories: List<MenuItemCategoryModel>){
        menuItemsCategoriesService.createOrUpdateMenuItemsCategories(categories)
    }
}