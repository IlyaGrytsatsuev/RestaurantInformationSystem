package com.example.demo.domain.usecases.users

import com.example.demo.domain.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteUsersUseCase @Autowired constructor(
    private val usersService: UsersService
) {
    operator fun invoke(idsList: List<Int>) {
        usersService.deleteUsers(idsList)
    }
}