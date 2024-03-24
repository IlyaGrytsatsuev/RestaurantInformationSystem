package com.example.demo.domain.usecases.users

import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.domain.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AddOrUpdateUsersUseCase @Autowired constructor(
    private val usersService: UsersService
) {
    operator fun invoke(usersList: List<UserAuthorizationModel>) {
        usersService.createOrUpdateUser(usersList)
    }
}