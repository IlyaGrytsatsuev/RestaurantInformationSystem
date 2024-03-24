package com.example.demo.domain.usecases.user_roles

import com.example.demo.domain.models.UserRoleModel
import com.example.demo.domain.services.UsersRolesService
import com.example.demo.presenter.api_models.user_roles.UserRolesResponseAndRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class GetUserRolesUseCase @Autowired constructor(
    private val usersRolesService: UsersRolesService
) {
    operator fun invoke(): List<UserRoleModel> {
        return usersRolesService.getUsersRolesList()
    }
}