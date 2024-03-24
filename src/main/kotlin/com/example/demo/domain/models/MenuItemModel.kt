package com.example.demo.domain.models

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

internal data class MenuItemModel(
    val id: Int,
    @NotEmpty
    val name: String,
    @Min(value = 1)
    val categoryId: Int,
    @Min(value = 1)
    val price: Int,
    val description: String,
    val imgPath: String,
)
