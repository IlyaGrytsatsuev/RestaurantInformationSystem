package com.example.demo.domain.models

data class StuffModel(
        val id: Int,
        val name:String,
        val surname: String,
        val role: String,
        val ordersIdsList: List<Int>
)
