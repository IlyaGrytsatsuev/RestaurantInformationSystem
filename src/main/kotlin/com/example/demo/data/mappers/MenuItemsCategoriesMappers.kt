package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun MenuItemCategoryEntity?.toMenuItemCategoryDomainModel(): MenuItemCategoryModel {
    if (this == null)
        throw NullReceivedException()
    return MenuItemCategoryModel(
            id = this.id,
            name = this.name,
            menuItemsModelsList =
            this.menuItemsEntitiesList
                    .toMenuItemModelsList()
    )
}

internal fun List<MenuItemCategoryEntity>.toMenuItemCategoryModelsList()
        : List<MenuItemCategoryModel> {
    return this.map { menuItemCategoryEntity ->
        menuItemCategoryEntity.toMenuItemCategoryDomainModel()
    }
}

internal fun MenuItemCategoryModel.toMenuItemCategoryDbModel(
        menuItemsEntitiesList: List<MenuItemEntity>
): MenuItemCategoryEntity {
    return MenuItemCategoryEntity(
            id = this.id,
            name = this.name,
            menuItemsEntitiesList =
            menuItemsEntitiesList
    )
}

internal fun MenuItemCategoryModel.toMenuItemCategoryEntityObjectForSaving(): MenuItemCategoryEntity {
    return this.toMenuItemCategoryDbModel(emptyList())
}