package com.example.demo.data.services

import com.example.demo.data.entities.OrderItemStatusEntity
import com.example.demo.data.mappers.toOrderItemStatusEntityObjectForSaving
import com.example.demo.data.mappers.toOrderItemStatusModelsList
import com.example.demo.data.repository.OrderItemsStatusesRepository
import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.domain.services.OrderItemsStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class OrderItemsStatusesServiceImpl @Autowired constructor(
    private val orderItemsStatusesRepository: OrderItemsStatusesRepository
) : OrderItemsStatusesService {
    override fun getOrderItemsStatusesList(): List<OrderItemStatusModel> {
        return orderItemsStatusesRepository.findAll().toOrderItemStatusModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderItemStatus(items: List<OrderItemStatusModel>) {
        items.forEach { item -> updateOrderItemStatusEntityOrSaveNewInstance(item) }
    }

    @Transactional
    override fun deleteOrderItemStatuses(items: List<Int>) {
        if (items.isEmpty()) {
            orderItemsStatusesRepository.deleteAll()
        } else {
            items.forEach { id ->
                orderItemsStatusesRepository.deleteById(id)
            }
        }
    }

    private fun updateOrderItemStatusEntityOrSaveNewInstance(
        orderItemStatusModel: OrderItemStatusModel
    ) {
        val orderItemStatusEntity = orderItemsStatusesRepository
            .findByIdOrNull(orderItemStatusModel.id)

        if (orderItemStatusEntity == null) {
            orderItemsStatusesRepository.save(
                orderItemStatusModel.toOrderItemStatusEntityObjectForSaving()
            )
        } else {
            orderItemStatusEntity.setEntityProperties(orderItemStatusModel)
            orderItemsStatusesRepository.save(orderItemStatusEntity)
        }
    }

    private fun OrderItemStatusEntity.setEntityProperties(
        orderItemStatusModel: OrderItemStatusModel
    ) {
        this.orderItemStatus = orderItemStatusModel.status
    }
}