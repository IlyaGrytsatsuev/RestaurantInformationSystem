package com.example.demo.domain.usecases.user_roles

import com.example.demo.domain.services.UsersRolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteUserRolesUseCase @Autowired constructor(
    private val usersRolesService: UsersRolesService
) {
    operator fun invoke(idsList: List<Int>) {
        usersRolesService.deleteUserRoles(idsList)
    }
}