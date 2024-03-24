package com.example.demo.domain.usecases.user_roles

import com.example.demo.domain.models.UserRoleModel
import com.example.demo.domain.services.UsersRolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AddOrUpdateUserRolesUseCase @Autowired constructor(
    private val usersRolesService: UsersRolesService
) {
    operator fun invoke(userRoles: List<UserRoleModel>) {
        usersRolesService.createOrUpdateUserRole(userRoles)
    }
}