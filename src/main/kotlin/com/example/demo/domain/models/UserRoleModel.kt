package com.example.demo.domain.models

data class UserRoleModel(
        val id: Int,
        val roleName: String,
        val usersList: List<StuffModel>
)
