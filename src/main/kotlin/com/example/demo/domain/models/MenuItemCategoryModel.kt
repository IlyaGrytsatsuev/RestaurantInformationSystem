package com.example.demo.domain.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

internal data class MenuItemCategoryModel(
        val id: Int,
        @NotBlank
        @NotEmpty
        val name: String,
)