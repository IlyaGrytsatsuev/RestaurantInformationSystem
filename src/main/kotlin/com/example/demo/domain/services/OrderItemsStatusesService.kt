package com.example.demo.domain.services

import com.example.demo.domain.models.OrderItemStatusModel

internal interface OrderItemsStatusesService {

    fun getOrderItemsStatusesList(): List<OrderItemStatusModel>
    fun createOrUpdateOrderItemStatus(items: List<OrderItemStatusModel>)
    fun deleteOrderItemStatuses(items: List<Int>)

}