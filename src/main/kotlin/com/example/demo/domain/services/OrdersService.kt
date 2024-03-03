package com.example.demo.domain.services

import com.example.demo.domain.models.OrderItemModel
import com.example.demo.domain.models.OrderModel

interface OrdersService {
    fun getOrdersList(): List<OrderModel>
    fun createOrUpdateOrder(items: List<OrderModel>)
}