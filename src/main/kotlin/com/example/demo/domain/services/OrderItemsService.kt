package com.example.demo.domain.services

import com.example.demo.domain.models.OrderItemModel

internal interface OrderItemsService {
    fun getOrderItemsList(): List<OrderItemModel>
    fun createOrUpdateOrderItems(items: List<OrderItemModel>)
    fun deleteOrderItems(items: List<Int>)

}