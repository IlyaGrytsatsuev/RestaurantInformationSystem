package com.example.demo.domain.services

import com.example.demo.domain.models.OrderStatusModel

interface OrderStatusesService {
    fun getOrderStatusesList(): List<OrderStatusModel>
    fun createOrUpdateOrderStatuses(items: List<OrderStatusModel>)
}