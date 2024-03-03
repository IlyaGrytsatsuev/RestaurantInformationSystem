package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.TableEntity
import com.example.demo.domain.models.TableModel
import com.example.demo.utils.exceptions.NullReceivedException

fun TableEntity?.toDomainModel(): TableModel {
    if (this == null)
        throw NullReceivedException()

    return TableModel(
            id = this.id,
            tableNumber = this.tableNumber,
            orders = this.orderEntitiesList.toStuffModelsList()
    )
}

fun List<TableEntity>.toStuffModelsList(): List<TableModel> {
    return this.map { tableEntity -> tableEntity.toDomainModel() }
}

fun TableModel.toDbModel(
        orderEntitiesList: List<OrderEntity>,
        tableNumber: Int? = null
): TableEntity {
    return TableEntity(
            id = this.id,
            tableNumber = tableNumber ?: this.tableNumber,
            orderEntitiesList = orderEntitiesList
    )
}

fun TableModel.toEntityObjectForSaving(
        tableNumber: Int
): TableEntity {
    return this.toDbModel(
            orderEntitiesList = emptyList(),
            tableNumber = tableNumber
    )
}