package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.UserEntity
import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.utils.exceptions.NullReceivedException

fun UserEntity?.toStuffModel(): StuffModel {
    if (this == null)
        throw NullReceivedException()
    return StuffModel(
            id = this.id,
            name = this.name,
            surname = this.surname,
            role = this.roleEntity.roleName,
            ordersIdsList =
            this.ordersEntitiesList.extractIdsList()
    )
}

fun List<UserEntity>.toStuffModelsList(): List<StuffModel> {
    return this.map { user -> user.toStuffModel() }
}

fun UserAuthorizationModel.toDbModel(
        ordersEntitiesList: List<OrderEntity>,
        roleEntity: UserRoleEntity?
): UserEntity {
    return UserEntity(
            id = this.id,
            username = this.username,
            password = this.password,
            name = this.name,
            surname = this.surname,
            roleEntity = roleEntity?: throw NullReceivedException(),
            ordersEntitiesList =
            ordersEntitiesList
    )
}

fun UserAuthorizationModel.toEntityObjectForSaving(
        roleEntity: UserRoleEntity?
): UserEntity {
    return this.toDbModel(emptyList(), roleEntity)
}