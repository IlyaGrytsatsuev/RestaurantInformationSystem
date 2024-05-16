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
import java.time.LocalDateTime

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
                .findByIdOrNull(orderModel.userId)
            val orderStatusEntity = orderStatusesRepository
                .findByIdOrNull(1)

            val orderModelWithCurTime = orderModel.copy(
                dateTime = LocalDateTime.now()
            )
            ordersRepository.save(
                orderModelWithCurTime.toOrderEntityObjectForSaving(
                    tableEntity = tableEntity,
                    waiterEntity = waiterEntity,
                    orderStatusEntity = orderStatusEntity
                )
            )
        } else {
            orderEntity.setEntityProperties(orderModel)
            ordersRepository.save(orderEntity)
        }
    }

    private fun OrderEntity.setEntityProperties(
        orderModel: OrderModel
    ) {
        val tableEntity = tablesRepository.findByIdOrNull(tableEntity?.id)
        this.tableEntity = tableEntity
        this.userEntity = usersRepository
            .findByIdOrNull(orderModel.userId)
        val orderStatus = orderStatusesRepository
            .findByIdOrNull(orderModel.statusId) ?: throw NullReceivedException()
        if (orderModel.statusId == 2 && tableEntity != null) {
            tableEntity.isFree = true
            tablesRepository.save(tableEntity)
        } else if (tableEntity != null) {
            tableEntity.isFree = false
            tablesRepository.save(tableEntity)
        }
        this.orderStatusEntity = orderStatus
    }
}