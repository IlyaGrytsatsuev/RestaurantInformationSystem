package com.example.demo.domain.models

import java.time.LocalDateTime

internal data class OrderModel(
        val id: Int,
        val waiterModel: StuffModel,
        val status: OrderStatusModel,
        var dateTime: LocalDateTime,
        val tableId: Int,
        val orderItemsList: List<OrderItemModel>
)