package com.example.demo.data.services

import com.example.demo.data.entities.OrderEntity
import com.example.demo.data.mappers.toOrderEntityObjectForSaving
import com.example.demo.data.mappers.toOrderModelsList
import com.example.demo.data.repository.OrderStatusesRepository
import com.example.demo.data.repository.OrdersRepository
import com.example.demo.data.repository.TablesRepository
import com.example.demo.data.repository.UsersRepository
import com.example.demo.domain.models.OrderModel
import com.example.demo.domain.services.OrdersService
import com.example.demo.utils.exceptions.NullReceivedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class OrdersServiceImpl @Autowired constructor(
    private val ordersRepository: OrdersRepository,
    private val tablesRepository: TablesRepository,
    private val usersRepository: UsersRepository,
    private val orderStatusesRepository: OrderStatusesRepository,
) : OrdersService {
    override fun getOrdersList(): List<OrderModel> {
        return ordersRepository.findAll().toOrderModelsList()
    }

    @Transactional
    override fun createOrUpdateOrder(items: List<OrderModel>) {
        items.forEach { orderModel ->
            updateOrderEntityOrSaveNewInstance(orderModel)
        }
    }

    @Transactional
    override fun deleteOrders(items: List<Int>) {
        if (items.isEmpty()) {
            ordersRepository.deleteAll()
        } else {
            items.forEach { id ->
                ordersRepository.deleteById(id)
            }
        }
    }

    private fun updateOrderEntityOrSaveNewInstance(
        orderModel: OrderModel
    ) {
        val orderEntity = ordersRepository
            .findByIdOrNull(orderModel.id)

        if (orderEntity == null) {

            val tableEntity = tablesRepository
                .findByIdOrNull(orderModel.tableId)
            val waiterEntity = usersRepository
                .findByIdOrNull(orderModel.waiterModel.id)
            val orderStatusEntity = orderStatusesRepository
                .findByIdOrNull(orderModel.status.id)

            ordersRepository.save(
                orderModel.toOrderEntityObjectForSaving(
                    tableEntity = tableEntity,
                    waiterEntity = waiterEntity,
                    orderStatusEntity = orderStatusEntity
                )
            )
        } else {
            orderEntity.setEntityProperties(orderModel)
        }
    }

    private fun OrderEntity.setEntityProperties(
        orderModel: OrderModel
    ) {
        this.tableEntity = tablesRepository
            .findByIdOrNull(orderModel.id) ?: throw NullReceivedException()
        this.waiterEntity = usersRepository
            .findByIdOrNull(orderModel.waiterModel.id)
        this.orderStatusEntity = orderStatusesRepository
            .findByIdOrNull(orderModel.status.id) ?: throw NullReceivedException()
    }
}