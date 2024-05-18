package com.example.demo.data.mappers

import com.example.demo.data.entities.*
import com.example.demo.domain.models.OrderModel
import com.example.demo.utils.exceptions.NullReceivedException
import java.time.LocalDateTime

internal fun OrderEntity?.toOrderDomainModel(): OrderModel {
    if (this == null)
        throw NullReceivedException()

    return OrderModel(
        id = this.id,
        userId = this.userEntity?.id ?: 0,
        statusId = this.orderStatusEntity.id,
        dateTime = this.dateTime.toString(),
        tableId = this.tableEntity?.id ?: -1,
    )
}

internal fun List<OrderEntity>.extractOrderIdsList(): List<Int> {
    return this.map { orderEntity -> orderEntity.id }
}

internal fun List<OrderEntity>.toOrderModelsList(): List<OrderModel> {
    return this.map { orderEntity -> orderEntity.toOrderDomainModel() }
}

internal fun OrderModel.toOrderDbModel(
    tableEntity: TableEntity?,
    waiterEntity: UserEntity?,
    localDateTime: LocalDateTime,
    orderStatusEntity: OrderStatusEntity?,
    orderItemsEntitiesList: List<OrderItemEntity>
): OrderEntity {
    return OrderEntity(
        id = this.id,
        tableEntity =
        tableEntity ?: throw NullReceivedException(),
        userEntity =
        waiterEntity ?: throw NullReceivedException(),
        orderStatusEntity =
        orderStatusEntity ?: throw NullReceivedException(),
        dateTime = localDateTime,
        orderItemsEntitiesList = orderItemsEntitiesList
    )
}

internal fun OrderModel.toOrderEntityObjectForSaving(
    tableEntity: TableEntity?,
    waiterEntity: UserEntity?,
    orderStatusEntity: OrderStatusEntity?,
    localDateTime: LocalDateTime
): OrderEntity {
    return this.toOrderDbModel(
        tableEntity = tableEntity,
        waiterEntity = waiterEntity,
        orderStatusEntity = orderStatusEntity,
        localDateTime = localDateTime,
        orderItemsEntitiesList = emptyList()
    )
}

