package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.UserEntity
import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.domain.models.UserModel
import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun UserEntity?.toStuffModel(): UserModel {
    if (this == null)
        throw NullReceivedException()
    return UserModel(
        id = this.id,
        name = this.name,
        surname = this.surname,
        role = this.roleEntity.id,
    )
}

internal fun List<UserEntity>.toStuffModelsList(): List<UserModel> {
    return this.map { user -> user.toStuffModel() }
}

internal fun UserEntity?.toUserAuthorizationModel(): UserAuthorizationModel {
    if (this == null)
        throw NullReceivedException()
    return UserAuthorizationModel(
        id = this.id,
        username = this.username,
        password = this.password,
        name = this.name,
        surname = this.surname,
        roleId = roleEntity.id,
    )
}

internal fun UserAuthorizationModel.toUserDbModel(
    ordersEntitiesList: List<OrderEntity>,
    roleEntity: UserRoleEntity?
): UserEntity {
    return UserEntity(
        id = this.id,
        username = this.username,
        password = this.password,
        name = this.name,
        surname = this.surname,
        roleEntity = roleEntity ?: throw NullReceivedException(),
        ordersEntitiesList =
        ordersEntitiesList
    )
}

internal fun UserAuthorizationModel.toUserEntityObjectForSaving(
    roleEntity: UserRoleEntity?
): UserEntity {
    return this.toUserDbModel(emptyList(), roleEntity)
}