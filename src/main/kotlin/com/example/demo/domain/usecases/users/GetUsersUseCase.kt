package com.example.demo.domain.usecases.users

import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class GetUsersUseCase @Autowired constructor(
    private val usersService: UsersService
) {
    operator fun invoke(): List<StuffModel> {
        return usersService.getStuffList()
    }
}