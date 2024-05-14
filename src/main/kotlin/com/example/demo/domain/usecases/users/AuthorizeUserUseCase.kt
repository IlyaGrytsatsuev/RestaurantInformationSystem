package com.example.demo.domain.usecases.users

import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.domain.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AuthorizeUserUseCase @Autowired constructor(
    private val usersService: UsersService
){
    operator fun invoke(user: UserAuthorizationModel): List<UserAuthorizationModel>{
        return usersService.authorizeUser(user)
    }

}