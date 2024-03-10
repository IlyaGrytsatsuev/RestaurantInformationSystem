package com.example.demo.domain.services

import com.example.demo.domain.models.OrderItemModel

interface OrderItemsService {
    fun getOrderItemsList(): List<OrderItemModel>
    fun createOrUpdateOrderItems(items: List<OrderItemModel>)
    fun deleteOrderItem(item: OrderItemModel)

}