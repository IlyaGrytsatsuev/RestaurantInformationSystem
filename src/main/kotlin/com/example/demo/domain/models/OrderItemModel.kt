package com.example.demo.domain.models

data class OrderItemModel(
        val id: Int,
        val orderId: Int,
        val menuItemModel: MenuItemModel,
        val quantity: Int,
        val status: OrderItemStatusModel
)
