package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun OrderItemStatusEntity?.toOrderItemStatusDomainModel(): OrderItemStatusModel {
    if (this == null)
        throw NullReceivedException()
    return OrderItemStatusModel(
            id = this.id,
            status = this.orderItemStatus,
            ordersItemsIdsList =
            this.orderItemsList.extractOrderItemIds()
    )
}

internal fun List<OrderItemStatusEntity>.toOrderItemStatusModelsList(): List<OrderItemStatusModel> {
    return this.map { orderItemStatusModel ->
        orderItemStatusModel.toOrderItemStatusDomainModel()
    }
}

internal fun OrderItemStatusModel.toOrderItemStatusDbModel(
        orderItemsList: List<OrderItemEntity>
): OrderItemStatusEntity {
    return OrderItemStatusEntity(
            id = this.id,
            orderItemStatus = this.status,
            orderItemsList = orderItemsList
    )
}

internal fun OrderItemStatusModel.toOrderItemStatusEntityObjectForSaving(): OrderItemStatusEntity {
    return this.toOrderItemStatusDbModel(emptyList())
}