package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.OrderStatusEntity
import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.utils.exceptions.NullReceivedException

fun OrderStatusEntity?.toDomainModel(): OrderStatusModel {
    if (this == null)
        throw NullReceivedException()
    return OrderStatusModel(
            id = this.id,
            status = this.name,
            ordersIdsList =
            this.ordersEntitiesList.extractIdsList()
    )
}

fun List<OrderStatusEntity>.toStuffModelsList(): List<OrderStatusModel> {
    return this.map { orderStatusEntity ->
        orderStatusEntity.toDomainModel()
    }
}

fun OrderStatusModel.toDbModel(
        ordersEntitiesList: List<OrderEntity>
): OrderStatusEntity {
    return OrderStatusEntity(
            id = this.id,
            name = this.status,
            ordersEntitiesList = ordersEntitiesList
    )
}

fun OrderStatusModel.toEntityObjectForSaving(): OrderStatusEntity {
    return this.toDbModel(emptyList())
}

