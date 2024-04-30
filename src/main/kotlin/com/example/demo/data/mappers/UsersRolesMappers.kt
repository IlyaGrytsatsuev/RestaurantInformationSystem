package com.example.demo.data.mappers

import com.example.demo.data.entities.UserEntity
import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun UserRoleEntity?.toUserRoleDomainModel(): UserRoleModel {
    if (this == null)
        throw NullReceivedException()
    return UserRoleModel(
            id = this.id,
            roleName = this.roleName,
    )
}

internal fun List<UserRoleEntity>.toUserRoleDomainModelsList(): List<UserRoleModel> {
    return this.map { role -> role.toUserRoleDomainModel() }
}

internal fun UserRoleModel.toUserRoleDbModel(
        usersEntitiesList: List<UserEntity>
): UserRoleEntity {
    return UserRoleEntity(
            id = this.id,
            roleName = this.roleName,
            usersEntitiesList = usersEntitiesList
    )
}

internal fun UserRoleModel.toUserRoleEntityObjectForSaving(): UserRoleEntity {
    return this.toUserRoleDbModel(emptyList())
}
