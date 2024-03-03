package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.utils.exceptions.NullReceivedException

fun MenuItemCategoryEntity?.toDomainModel(): MenuItemCategoryModel {
    if (this == null)
        throw NullReceivedException()
    return MenuItemCategoryModel(
            id = this.id,
            name = this.name,
            menuItemsModelsList =
            this.menuItemsEntitiesList
                    .toStuffModelsList()
    )
}

fun List<MenuItemCategoryEntity>.toStuffModelsList()
        : List<MenuItemCategoryModel> {
    return this.map { menuItemCategoryEntity ->
        menuItemCategoryEntity.toDomainModel()
    }
}

fun MenuItemCategoryModel.toDbModel(
        menuItemsEntitiesList: List<MenuItemEntity>
): MenuItemCategoryEntity {
    return MenuItemCategoryEntity(
            id = this.id,
            name = this.name,
            menuItemsEntitiesList =
            menuItemsEntitiesList
    )
}

fun MenuItemCategoryModel.toEntityObjectForSaving(): MenuItemCategoryEntity {
    return this.toDbModel(emptyList())
}