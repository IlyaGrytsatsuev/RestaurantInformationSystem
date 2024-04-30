package com.example.demo.data.services

import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.mappers.toMenuItemEntityObjectForSaving
import com.example.demo.data.mappers.toMenuItemModelsList
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.data.repository.MenuItemsRepository
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.services.MenuItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class MenuItemsServiceImpl @Autowired constructor(
        private val menuItemsRepository: MenuItemsRepository,
        private val menuItemsCategoriesRepository: MenuItemsCategoriesRepository,
) : MenuItemsService {

    override fun getMenuItemsList(): List<MenuItemModel> {
        return menuItemsRepository.findAll().toMenuItemModelsList()
    }

    @Transactional
    override fun createOrUpdateMenuItems(items: List<MenuItemModel>) {
        items.forEach { menuItemModel ->
            updateMenuItemEntityOrSaveNewInstance(menuItemModel)
        }
    }

    @Transactional
    override fun deleteMenuItems(ids: List<Int>) {
        if(ids.isEmpty()){
            menuItemsRepository.deleteAll()
        }
        else{
            ids.forEach {id ->
                menuItemsRepository.deleteById(id)
            }
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
                        .toMenuItemEntityObjectForSaving(categoryEntity)
            )
        } else {
            menuItemEntity.setEntityProperties(menuItemModel)
            menuItemsRepository.save(menuItemEntity)
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