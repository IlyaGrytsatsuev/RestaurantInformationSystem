package com.example.demo.domain.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

internal data class OrderItemStatusModel(
        val id: Int,
        @NotEmpty
        @NotBlank
        val status: String,
)
