package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.OrderStatusEntity
import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun OrderStatusEntity?.toOrderStatusDomainModel(): OrderStatusModel {
    if (this == null)
        throw NullReceivedException()
    return OrderStatusModel(
            id = this.id,
            status = this.name,
            ordersIdsList =
            this.ordersEntitiesList.extractOrderIdsList()
    )
}

internal fun List<OrderStatusEntity>.toOrderStatusModelsList(): List<OrderStatusModel> {
    return this.map { orderStatusEntity ->
        orderStatusEntity.toOrderStatusDomainModel()
    }
}

internal fun OrderStatusModel.toOrderStatusDbModel(
        ordersEntitiesList: List<OrderEntity>
): OrderStatusEntity {
    return OrderStatusEntity(
            id = this.id,
            name = this.status,
            ordersEntitiesList = ordersEntitiesList
    )
}

internal fun OrderStatusModel.toOrderStatusEntityObjectForSaving(): OrderStatusEntity {
    return this.toOrderStatusDbModel(emptyList())
}

