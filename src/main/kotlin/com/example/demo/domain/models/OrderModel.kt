package com.example.demo.domain.models

import java.time.LocalDateTime

internal data class OrderModel(
        val id: Int,
        val userId: Int,
        val statusId: Int,
        var dateTime: String,
        val tableId: Int,
)