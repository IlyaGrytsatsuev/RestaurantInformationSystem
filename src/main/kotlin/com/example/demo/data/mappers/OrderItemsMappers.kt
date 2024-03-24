package com.example.demo.data.mappers

import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.domain.models.OrderItemModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun OrderItemEntity?.toOrderItemDomainModel(): OrderItemModel {
    if (this == null)
        throw NullReceivedException()
    return OrderItemModel(
            id = this.id,
            orderId = this.orderEntity.id,
            menuItemModel = this.menuItemEntity.toMenuItemDomainModel(),
            quantity = this.quantity,
            status = this.status.toOrderItemStatusDomainModel()
    )
}

internal fun List<OrderItemEntity>.toOrderItemModelsList(): List<OrderItemModel> {
    return this.map { orderItemEntity -> orderItemEntity.toOrderItemDomainModel() }
}

internal fun List<OrderItemEntity>.extractOrderItemIds(): List<Int>{
    return this.map { orderItemEntity -> orderItemEntity.id }
}

internal fun OrderItemModel.toOrderItemDbModel(
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
