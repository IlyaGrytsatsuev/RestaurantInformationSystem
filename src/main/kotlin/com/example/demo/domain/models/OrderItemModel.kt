package com.example.demo.domain.models

internal data class OrderItemModel(
        val id: Int,
        val orderId: Int,
        val menuItemId: Int,
        val quantity: Int,
        val statusId: Int
)
