package com.example.demo.data.services

import com.example.demo.data.mappers.toMenuItemCategoryEntityObjectForSaving
import com.example.demo.data.mappers.toMenuItemCategoryModelsList
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.services.MenuItemsCategoriesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class MenuItemsCategoriesServiceImpl @Autowired constructor(
    private val menuItemsCategoriesRepository: MenuItemsCategoriesRepository,
) : MenuItemsCategoriesService {

    override fun getMenuItemsCategoriesList(): List<MenuItemCategoryModel> {
        return menuItemsCategoriesRepository.findAll().toMenuItemCategoryModelsList()
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
        if (ids.isEmpty()) {
            menuItemsCategoriesRepository.deleteAll()
        } else {
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
                menuItemCategoryModel.toMenuItemCategoryEntityObjectForSaving()
            )

        } else {
            menuItemCategoryEntity.name = menuItemCategoryModel.name
            menuItemsCategoriesRepository.save(menuItemCategoryEntity)
        }
    }

}