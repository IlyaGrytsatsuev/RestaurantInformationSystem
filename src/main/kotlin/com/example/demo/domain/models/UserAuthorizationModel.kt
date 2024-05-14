package com.example.demo.domain.models

import com.example.demo.data.entities.UserRoleEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

internal data class UserAuthorizationModel(
        val id: Int,
        @NotEmpty
        @NotBlank
        val username: String,
        @NotEmpty
        @NotBlank
        val password: String,
        val name: String,
        val surname: String,
        val roleId: Int
)
