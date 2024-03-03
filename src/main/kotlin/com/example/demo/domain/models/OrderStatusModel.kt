package com.example.demo.domain.models

data class OrderStatusModel(
        val id: Int,
        val status: String,
        val ordersIdsList: List<Int>
)
