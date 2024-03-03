package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.domain.models.OrderItemModel
import com.example.demo.utils.exceptions.NullReceivedException

fun OrderItemEntity?.toDomainModel(): OrderItemModel {
    if (this == null)
        throw NullReceivedException()
    return OrderItemModel(
            id = this.id,
            orderId = this.orderEntity.id,
            menuItemModel = this.menuItemEntity.toDomainModel(),
            quantity = this.quantity,
            status = this.status.toDomainModel()
    )
}

fun List<OrderItemEntity>.toStuffModelsList(): List<OrderItemModel> {
    return this.map { orderItemEntity -> orderItemEntity.toDomainModel() }
}

fun List<OrderItemEntity>.extractIds(): List<Int>{
    return this.map { orderItemEntity -> orderItemEntity.id }
}

fun OrderItemModel.toDbModel(
        orderEntity: OrderEntity?,
        menuItemEntity: MenuItemEntity?,
        itemStatusEntity: OrderItemStatusEntity?
): OrderItemEntity {
    if(orderEntity == null || menuItemEntity == null)
        throw NullReceivedException()
    return OrderItemEntity(
            id = this.id,
            orderEntity = orderEntity,
            menuItemEntity = menuItemEntity,
            quantity = this.quantity,
            status = itemStatusEntity ?: throw NullReceivedException()
    )
}
