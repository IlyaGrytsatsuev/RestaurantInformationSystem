package com.example.demo.domain.services

import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.models.OrderItemModel

interface OrderItemsService {
    fun getOrderItemsList(): List<OrderItemModel>
    fun createOrUpdateOrderItems(items: List<OrderItemModel>)
}