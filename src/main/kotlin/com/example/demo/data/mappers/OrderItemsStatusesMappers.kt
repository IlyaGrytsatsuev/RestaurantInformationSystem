package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.utils.exceptions.NullReceivedException

fun OrderItemStatusEntity?.toDomainModel(): OrderItemStatusModel {
    if (this == null)
        throw NullReceivedException()
    return OrderItemStatusModel(
            id = this.id,
            status = this.orderItemStatus,
            ordersItemsIdsList =
            this.orderItemsList.extractIds()
    )
}

fun List<OrderItemStatusEntity>.toStuffModelsList(): List<OrderItemStatusModel> {
    return this.map { orderItemStatusModel ->
        orderItemStatusModel.toDomainModel()
    }
}

fun OrderItemStatusModel.toDbModel(
        orderItemsList: List<OrderItemEntity>
): OrderItemStatusEntity {
    return OrderItemStatusEntity(
            id = this.id,
            orderItemStatus = this.status,
            orderItemsList = orderItemsList
    )
}

fun OrderItemStatusModel.toEntityObjectForSaving(): OrderItemStatusEntity {
    return this.toDbModel(emptyList())
}