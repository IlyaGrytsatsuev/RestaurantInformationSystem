package com.example.demo.domain.models

internal data class UserRoleModel(
        val id: Int,
        val roleName: String,
        val usersList: List<StuffModel>
)
