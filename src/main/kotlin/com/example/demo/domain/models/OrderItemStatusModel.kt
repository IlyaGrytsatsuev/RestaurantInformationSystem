package com.example.demo.domain.models

data class OrderItemStatusModel(
        val id: Int,
        val status: String,
        val ordersItemsIdsList: List<Int>
)
