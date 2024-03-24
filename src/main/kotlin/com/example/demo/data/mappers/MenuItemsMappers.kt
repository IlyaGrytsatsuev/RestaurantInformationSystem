package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun MenuItemEntity?.toMenuItemDomainModel(): MenuItemModel {
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

internal fun List<MenuItemEntity>.toMenuItemModelsList(): List<MenuItemModel> {
    return this.map { menuItemEntity -> menuItemEntity.toMenuItemDomainModel() }
}


internal fun MenuItemModel.toMenuItemDbModel(
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

internal fun MenuItemModel.toMenuItemEntityObjectForSaving(
        categoryEntity: MenuItemCategoryEntity?
): MenuItemEntity {
    return this.toMenuItemDbModel(
            itemCategoryEntity = categoryEntity,
            orderItemEntitiesList = emptyList()
    )
}
