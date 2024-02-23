package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.utils.exceptions.NullReceivedException

fun MenuItemEntity?.toDomainModel(): MenuItemModel {
    if (this == null)
        throw NullReceivedException()
    return MenuItemModel(
            id = this.id,
            name = this.name,
            categoryId = this.menuItemCategoryEntity?.id ?: 0,
            price = this.price,
            description = this.description ?: "",
            imgPath = this.imgPath ?: ""
    )
}

fun List<MenuItemEntity>.toDomainModelsList(): List<MenuItemModel> {
    return this.map { menuItemEntity -> menuItemEntity.toDomainModel() }
}


fun MenuItemModel.toDbModel(
        itemCategoryEntity: MenuItemCategoryEntity?,
        orderItemEntitiesList: List<OrderItemEntity>
): MenuItemEntity {
    return MenuItemEntity(
            id = this.id,
            name = this.name,
            menuItemCategoryEntity = itemCategoryEntity,
            price = this.price,
            description = this.description,
            imgPath = this.imgPath,
            orderItemsEntitiesList = orderItemEntitiesList
    )
}
