package com.example.demo.data.services

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.mappers.toDbModel
import com.example.demo.data.mappers.toDomainModel
import com.example.demo.data.mappers.toDomainModelsList
import com.example.demo.data.repository.MenuItemRepository
import com.example.demo.data.repository.MenuItemsCategoriesRepository
import com.example.demo.data.repository.OrderItemsRepository
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.services.MenuItemsService
import com.example.demo.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuItemsServiceImpl @Autowired constructor(
        private val menuItemRepository: MenuItemRepository,
        private val menuItemsCategoriesRepository: MenuItemsCategoriesRepository,
        private val orderItemsRepository: OrderItemsRepository,
) : MenuItemsService {
    override fun getMenuItemsList(): List<MenuItemModel> {
        return menuItemRepository.findAll().toDomainModelsList()
    }

    fun getMenuItemsByIds(ids: List<Int>):List<MenuItemModel>{
        return menuItemRepository.findAllById(ids).map { menuItemEntity ->
            menuItemEntity.toDomainModel()
        }
    }
    override fun createOrUpdateItems(items: List<MenuItemModel>) {
        val itemsEntitiesList: List<MenuItemEntity> =
                items.map { menuItemModel ->
                    getMenuItemEntity(menuItemModel)
                }
        menuItemRepository.saveAll(itemsEntitiesList)
    }

    private fun getMenuItemEntity(menuItemModel: MenuItemModel): MenuItemEntity {
        return menuItemModel.toDbModel(
                itemCategoryEntity =
                getItemCategory(menuItemModel.categoryId),
                orderItemEntitiesList = getOrdersItems(menuItemModel.id)
        )
    }

    private fun getItemCategory(id: Int): MenuItemCategoryEntity? {
        if (id == Constants.UNDEFINED_ID)
            return null
        return menuItemsCategoriesRepository.findByIdOrNull(id)
    }

    private fun getOrdersItems(menuItemId: Int): List<OrderItemEntity> {
        return orderItemsRepository.findAllByMenuItemId(menuItemId)
    }

}