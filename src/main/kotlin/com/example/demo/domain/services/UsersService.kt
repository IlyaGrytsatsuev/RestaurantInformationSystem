package com.example.demo.domain.services

import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.models.UserAuthorizationModel

internal interface UsersService {
    fun getStuffList(): List<StuffModel>
    fun createOrUpdateUser(items: List<UserAuthorizationModel>)
    fun deleteUsers(items: List<Int>)

}