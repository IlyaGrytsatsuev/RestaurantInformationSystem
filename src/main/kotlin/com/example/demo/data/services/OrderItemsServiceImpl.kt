package com.example.demo.data.services

import com.example.demo.data.entities.OrderItemEntity
import com.example.demo.data.mappers.toDbModel
import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.repository.MenuItemsRepository
import com.example.demo.data.repository.OrderItemsRepository
import com.example.demo.data.repository.OrderItemsStatusesRepository
import com.example.demo.data.repository.OrdersRepository
import com.example.demo.domain.models.OrderItemModel
import com.example.demo.domain.services.OrderItemsService
import com.example.demo.utils.OrderItemStatus
import com.example.demo.utils.exceptions.NullReceivedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderItemsServiceImpl @Autowired constructor(
        private val orderItemsRepository: OrderItemsRepository,
        private val ordersRepository: OrdersRepository,
        private val menuItemsRepository: MenuItemsRepository,
        private val orderItemsStatusesRepository: OrderItemsStatusesRepository,
): OrderItemsService {
    override fun getOrderItemsList(): List<OrderItemModel> {
        return orderItemsRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderItems(items: List<OrderItemModel>) {
        items.forEach { orderItemModel ->
            updateOrderItemEntityOrSaveNewInstance(orderItemModel)
        }
    }
    @Transactional
    override fun deleteOrderItem(item: OrderItemModel) {
        orderItemsRepository.deleteById(item.id)
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
                    .findByIdOrNull(orderItemModel.menuItemModel.id)

            val status = orderItemsStatusesRepository.findByStatusOrNull(
                    OrderItemStatus.RECEIVED.status
            )
            orderItemsRepository.save(
                    orderItemModel.toDbModel(
                            orderEntity = orderEntity,
                            menuItemEntity = menuItemEntity,
                            itemStatusEntity = status
                    )
            )
        } else {
            orderItemEntity.setEntityProperties(orderItemModel)
        }
    }


    private fun OrderItemEntity.setEntityProperties(
            orderItemModel: OrderItemModel
    ) {
        this.menuItemEntity = menuItemsRepository
                .findByIdOrNull(orderItemModel.menuItemModel.id)
                ?: throw NullReceivedException()
    }
}