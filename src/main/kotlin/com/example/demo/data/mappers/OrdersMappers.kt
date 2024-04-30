package com.example.demo.data.mappers

import com.example.demo.data.entities.*
import com.example.demo.domain.models.OrderModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun OrderEntity?.toOrderDomainModel(): OrderModel {
    if (this == null)
        throw NullReceivedException()

    return OrderModel(
            id = this.id,
            waiterId = this.waiterEntity?.id ?: 0,
            statusId = this.orderStatusEntity.id,
            dateTime = this.dateTime,
            tableId = this.tableEntity.id,
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

internal fun OrderModel.toOrderEntityObjectForSaving(
        tableEntity: TableEntity?,
        waiterEntity: UserEntity?,
        orderStatusEntity: OrderStatusEntity?,
): OrderEntity {
    return this.toOrderDbModel(
            tableEntity = tableEntity,
            waiterEntity = waiterEntity,
            orderStatusEntity = orderStatusEntity,
            emptyList()
    )
}

