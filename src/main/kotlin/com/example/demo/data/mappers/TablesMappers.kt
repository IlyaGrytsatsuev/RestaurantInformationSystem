package com.example.demo.data.mappers

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.entities.TableEntity
import com.example.demo.domain.models.TableModel
import com.example.demo.utils.exceptions.NullReceivedException

internal fun TableEntity?.toTableDomainModel(): TableModel {
    if (this == null)
        throw NullReceivedException()

    return TableModel(
        id = this.id,
        tableNumber = this.tableNumber,
        orders = this.orderEntitiesList.toOrderModelsList()
    )
}

internal fun List<TableEntity>.toTablesModelsList(): List<TableModel> {
    return this.map { tableEntity -> tableEntity.toTableDomainModel() }
}

internal fun TableModel.toTableDbModel(
    orderEntitiesList: List<OrderEntity>,
    tableNumber: Int? = null
): TableEntity {
    return TableEntity(
        id = this.id,
        tableNumber = tableNumber ?: this.tableNumber,
        orderEntitiesList = orderEntitiesList
    )
}

internal fun createTableEntityObjectForSaving(
    tableNumber: Int
): TableEntity {
    return TableEntity(
        id = -1,
        tableNumber = tableNumber,
        orderEntitiesList = emptyList(),
    )

}