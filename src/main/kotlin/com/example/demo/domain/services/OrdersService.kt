package com.example.demo.domain.services

import com.example.demo.domain.models.OrderModel

internal interface OrdersService {
    fun getOrdersList(): List<OrderModel>
    fun createOrUpdateOrder(items: List<OrderModel>)
    fun deleteOrders(items: List<Int>)

}