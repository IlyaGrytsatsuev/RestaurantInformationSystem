package com.example.demo.data.mappers

import com.example.demo.data.entities.UserEntity
import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.utils.exceptions.NullReceivedException

fun UserRoleEntity?.toDomainModel(): UserRoleModel {
    if (this == null)
        throw NullReceivedException()
    return UserRoleModel(
            id = this.id,
            roleName = this.roleName,
            usersList = this.usersEntitiesList.toStuffModelsList()
    )
}

fun List<UserRoleEntity>.toStuffModelsList(): List<UserRoleModel> {
    return this.map { role -> role.toDomainModel() }
}

fun UserRoleModel.toDbModel(
        usersEntitiesList: List<UserEntity>
): UserRoleEntity {
    return UserRoleEntity(
            id = this.id,
            roleName = this.roleName,
            usersEntitiesList = usersEntitiesList
    )
}

fun UserRoleModel.toEntityObjectForSaving(): UserRoleEntity {
    return this.toDbModel(emptyList())
}
