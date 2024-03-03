package com.example.demo.data.mappers

import com.example.demo.data.entities.*
import com.example.demo.domain.models.OrderModel
import com.example.demo.utils.exceptions.NullReceivedException

fun OrderEntity?.toDomainModel(): OrderModel {
    if (this == null)
        throw NullReceivedException()

    return OrderModel(
            id = this.id,
            waiterModel = this.waiterEntity.toStuffModel(),
            status = this.orderStatusEntity.toDomainModel(),
            dateTime = this.dateTime,
            tableId = this.tableEntity.id,
            orderItemsList =
            this.orderItemsEntitiesList.toStuffModelsList()
    )
}

fun List<OrderEntity>.extractIdsList(): List<Int> {
    return this.map { orderEntity -> orderEntity.id }
}

fun List<OrderEntity>.toStuffModelsList(): List<OrderModel> {
    return this.map { orderEntity -> orderEntity.toDomainModel() }
}

fun OrderModel.toDbModel(
        tableEntity: TableEntity?,
        waiterEntity: UserEntity?,
        orderStatusEntity: OrderStatusEntity?,
        orderItemsEntitiesList: List<OrderItemEntity>
): OrderEntity {
    return OrderEntity(
            id = this.id,
            tableEntity =
            tableEntity ?: throw NullReceivedException(),
            waiterEntity =
            waiterEntity ?: throw NullReceivedException(),
            orderStatusEntity =
            orderStatusEntity ?: throw NullReceivedException(),
            dateTime = this.dateTime,
            orderItemsEntitiesList = orderItemsEntitiesList
    )
}

fun OrderModel.toEntityObjectForSaving(
        tableEntity: TableEntity?,
        waiterEntity: UserEntity?,
        orderStatusEntity: OrderStatusEntity?,
): OrderEntity {
    return this.toDbModel(
            tableEntity = tableEntity,
            waiterEntity = waiterEntity,
            orderStatusEntity = orderStatusEntity,
            emptyList()
    )
}

