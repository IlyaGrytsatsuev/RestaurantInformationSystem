package com.example.demo.domain.services

import com.example.demo.domain.models.UserRoleModel

interface UsersRolesService {
    fun getUsersRolesList(): List<UserRoleModel>
    fun createOrUpdateUserRole(items: List<UserRoleModel>)
    fun deleteUserRoles(items: List<UserRoleModel>)

}