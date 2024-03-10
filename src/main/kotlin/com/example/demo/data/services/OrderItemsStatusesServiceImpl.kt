package com.example.demo.data.services

import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.OrderItemsStatusesRepository
import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.domain.services.OrderItemsStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderItemsStatusesServiceImpl @Autowired constructor(
        private val orderItemsStatusesRepository: OrderItemsStatusesRepository
): OrderItemsStatusesService{
    override fun getOrderItemsStatusesList(): List<OrderItemStatusModel> {
        return orderItemsStatusesRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderItemStatus(items: List<OrderItemStatusModel>) {
        items.forEach { item -> updateOrderItemStatusEntityOrSaveNewInstance(item) }
    }

    @Transactional
    override fun deleteOrderItemStatus(item: OrderItemStatusModel) {
        orderItemsStatusesRepository.deleteById(item.id)
    }

    private fun updateOrderItemStatusEntityOrSaveNewInstance(
            orderItemStatusModel: OrderItemStatusModel
    ) {
        val orderItemStatusEntity = orderItemsStatusesRepository
                .findByIdOrNull(orderItemStatusModel.id)

        if (orderItemStatusEntity == null) {
            orderItemsStatusesRepository.save(
                    orderItemStatusModel.toEntityObjectForSaving()
            )
        } else {
            orderItemStatusEntity.setEntityProperties(orderItemStatusModel)
        }
    }

    private fun OrderItemStatusEntity.setEntityProperties(
            orderItemStatusModel: OrderItemStatusModel
    ) {
        this.orderItemStatus = orderItemStatusModel.status
    }
}