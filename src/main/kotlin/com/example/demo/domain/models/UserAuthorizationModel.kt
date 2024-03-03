package com.example.demo.domain.models

import com.example.demo.data.entities.UserRoleEntity

data class UserAuthorizationModel(
        val id: Int,
        val username: String,
        val password: String,
        val name: String,
        val surname: String,
        val roleId: Int
)
