package com.example.demo.domain.models

data class TableModel(
        val id: Int,
        val tableNumber: Int,
        val orders: List<OrderModel>
)
