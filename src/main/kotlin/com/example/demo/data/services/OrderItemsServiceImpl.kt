package com.example.demo.data.services

import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.mappers.toOrderItemDbModel
import com.example.demo.data.mappers.toOrderItemModelsList
import com.example.demo.data.repository.MenuItemsRepository
import com.example.demo.data.repository.OrderItemsRepository
import com.example.demo.data.repository.OrderItemsStatusesRepository
import com.example.demo.data.repository.OrdersRepository
import com.example.demo.domain.models.OrderItemModel
import com.example.demo.domain.services.OrderItemsService
import com.example.demo.utils.exceptions.NullReceivedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class OrderItemsServiceImpl @Autowired constructor(
    private val orderItemsRepository: OrderItemsRepository,
    private val ordersRepository: OrdersRepository,
    private val menuItemsRepository: MenuItemsRepository,
    private val orderItemsStatusesRepository: OrderItemsStatusesRepository,
) : OrderItemsService {
    override fun getOrderItemsList(): List<OrderItemModel> {
        return orderItemsRepository.findAll().toOrderItemModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderItems(items: List<OrderItemModel>) {
        items.forEach { orderItemModel ->
            updateOrderItemEntityOrSaveNewInstance(orderItemModel)
        }
    }

    @Transactional
    override fun deleteOrderItems(items: List<Int>) {
        if (items.isEmpty()) {
            orderItemsRepository.deleteAll()
        } else {
            items.forEach { id ->
                orderItemsRepository.deleteById(id)
            }
        }
    }

    private fun updateOrderItemEntityOrSaveNewInstance(
        orderItemModel: OrderItemModel
    ) {
        val orderItemEntity = orderItemsRepository.findByIdOrNull(
            orderItemModel.id
        )
        if (orderItemEntity == null) {

            val orderEntity = ordersRepository
                .findByIdOrNull(orderItemModel.orderId)
            val menuItemEntity = menuItemsRepository
                .findByIdOrNull(orderItemModel.menuItemId)

            val status = orderItemsStatusesRepository.findByIdOrNull(
                orderItemModel.statusId
            )
            orderItemsRepository.save(
                orderItemModel.toOrderItemDbModel(
                    orderEntity = orderEntity,
                    menuItemEntity = menuItemEntity,
                    itemStatusEntity = status
                )
            )
        } else {
            orderItemEntity.setEntityProperties(orderItemModel)
            orderItemsRepository.save(orderItemEntity)
        }
    }


    private fun OrderItemEntity.setEntityProperties(
        orderItemModel: OrderItemModel
    ) {
        this.menuItemEntity = menuItemsRepository
            .findByIdOrNull(orderItemModel.menuItemId)
            ?: throw NullReceivedException()
    }
}