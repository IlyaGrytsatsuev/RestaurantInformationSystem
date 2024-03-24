package com.example.demo.domain.models

internal data class TableModel(
        val id: Int,
        val tableNumber: Int,
        val orders: List<OrderModel>
)
