package com.example.demo.data.services

import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.MenuItemsRepository
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.services.MenuItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MenuItemsServiceImpl @Autowired constructor(
        private val menuItemsRepository: MenuItemsRepository,
        private val menuItemsCategoriesRepository: MenuItemsCategoriesRepository,
) : MenuItemsService {

    override fun getMenuItemsList(): List<MenuItemModel> {
        return menuItemsRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createOrUpdateMenuItems(items: List<MenuItemModel>) {
        items.forEach { menuItemModel ->
            updateMenuItemEntityOrSaveNewInstance(menuItemModel)
        }
    }

    private fun updateMenuItemEntityOrSaveNewInstance(
            menuItemModel: MenuItemModel
    ) {
        val menuItemEntity = menuItemsRepository.findByIdOrNull(
                menuItemModel.id
        )
        if (menuItemEntity == null) {

            val categoryEntity = menuItemsCategoriesRepository
                    .findByIdOrNull(menuItemModel.categoryId)

            menuItemsRepository.save(
                    menuItemModel
                            .toEntityObjectForSaving(categoryEntity)
            )
        } else {
            menuItemEntity.setEntityProperties(menuItemModel)
        }
    }


    private fun MenuItemEntity.setEntityProperties(
            menuItemModel: MenuItemModel
    ) {
        this.name = menuItemModel.name
        this.menuItemCategoryEntity = menuItemsCategoriesRepository
                .findByIdOrNull(menuItemModel.id)
        this.price = menuItemModel.price
        this.description = menuItemModel.description
        this.imgPath = menuItemModel.imgPath
    }

}