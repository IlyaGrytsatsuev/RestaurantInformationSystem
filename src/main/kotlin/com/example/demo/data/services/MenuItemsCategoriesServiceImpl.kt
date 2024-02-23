package com.example.demo.data.services

import com.example.demo.data.mappers.toDbModel
import com.example.demo.data.mappers.toDomainModelsList
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.services.MenuItemsCategoriesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuItemsCategoriesServiceImpl @Autowired constructor(
        private val menuItemsCategoriesRepository
        : MenuItemsCategoriesRepository
) : MenuItemsCategoriesService {

    override fun getMenuItemsCategoriesList(): List<MenuItemCategoryModel> {
        return menuItemsCategoriesRepository.findAll().toDomainModelsList()
    }

    override fun createOrUpdateMenuItemsCategories(menuItemsCategories: List<MenuItemCategoryModel>) {
        val itemsCategoriesEntitiesList = menuItemsCategories.map { menuItemCategoryModel ->
            //menuItemCategoryModel.toDbModel()
        }
    }


}