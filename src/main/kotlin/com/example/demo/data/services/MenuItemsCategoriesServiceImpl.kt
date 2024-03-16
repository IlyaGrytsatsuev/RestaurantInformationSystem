package com.example.demo.data.services

import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.services.MenuItemsCategoriesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuItemsCategoriesServiceImpl @Autowired constructor(
        private val menuItemsCategoriesRepository: MenuItemsCategoriesRepository,
) : MenuItemsCategoriesService {

    override fun getMenuItemsCategoriesList(): List<MenuItemCategoryModel> {
        return menuItemsCategoriesRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createOrUpdateMenuItemsCategories(
            menuItemsCategories: List<MenuItemCategoryModel>
    ) {
        menuItemsCategories.map { menuItemCategoryModel ->
            updateItemCategoryEntityOrSaveNewInstance(menuItemCategoryModel)
        }
    }

    @Transactional
    override fun deleteMenuItemCategories(ids: List<Int>) {
        if(ids.isEmpty()) {
            menuItemsCategoriesRepository.deleteAll()
        }
        else {
            ids.forEach { id ->
                menuItemsCategoriesRepository.deleteById(id)
            }
        }
    }

    private fun updateItemCategoryEntityOrSaveNewInstance(
            menuItemCategoryModel: MenuItemCategoryModel
    ) {
        val menuItemCategoryEntity = menuItemsCategoriesRepository.findByIdOrNull(
                menuItemCategoryModel.id
        )
        if (menuItemCategoryEntity == null) {

            menuItemsCategoriesRepository.save(
                    menuItemCategoryModel.toEntityObjectForSaving()
            )

        } else {
            menuItemCategoryEntity.name = menuItemCategoryModel.name
        }
    }

}