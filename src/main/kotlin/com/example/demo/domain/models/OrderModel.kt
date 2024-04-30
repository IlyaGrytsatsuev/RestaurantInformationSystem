package com.example.demo.domain.models

import java.time.LocalDateTime

internal data class OrderModel(
        val id: Int,
        val waiterId: Int,
        val statusId: Int,
        var dateTime: LocalDateTime,
        val tableId: Int,
)