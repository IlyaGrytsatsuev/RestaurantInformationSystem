package com.example.demo.domain.models

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

internal data class OrderStatusModel(
        val id: Int,
        @NotBlank
        @NotEmpty
        val status: String,
        val ordersIdsList: List<Int>
)
