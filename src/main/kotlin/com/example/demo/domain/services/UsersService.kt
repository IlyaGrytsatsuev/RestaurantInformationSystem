package com.example.demo.domain.services

import com.example.demo.domain.models.UserModel
import com.example.demo.domain.models.UserAuthorizationModel

internal interface UsersService {
    fun getUsersList(): List<UserModel>
    fun authorizeUser(user: UserAuthorizationModel): List<UserAuthorizationModel>
    fun createOrUpdateUser(items: List<UserAuthorizationModel>)
    fun deleteUsers(items: List<Int>)

}