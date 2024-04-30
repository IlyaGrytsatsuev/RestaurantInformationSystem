package com.example.demo.data.services

import com.example.demo.data.mappers.toOrderStatusEntityObjectForSaving
import com.example.demo.data.mappers.toOrderStatusModelsList
import com.example.demo.data.repository.OrderStatusesRepository
import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.domain.services.OrderStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class OrderStatusesServiceImpl @Autowired constructor(
    private val orderStatusesRepository: OrderStatusesRepository
) : OrderStatusesService {

    override fun getOrderStatusesList(): List<OrderStatusModel> {
        return orderStatusesRepository.findAll().toOrderStatusModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderStatuses(items: List<OrderStatusModel>) {
        items.forEach { orderStatusModel ->
            updateOrderStatusEntityOrSaveNewInstance(orderStatusModel)
        }
    }


    @Transactional
    override fun deleteOrderStatuses(items: List<Int>) {
        if (items.isEmpty()) {
            orderStatusesRepository.deleteAll()
        } else {
            items.forEach { id ->
                orderStatusesRepository.deleteById(id)
            }
        }
    }

    private fun updateOrderStatusEntityOrSaveNewInstance(
        orderStatusModel: OrderStatusModel
    ) {
        val orderStatusEntity = orderStatusesRepository.findByIdOrNull(
            orderStatusModel.id
        )
        if (orderStatusEntity == null) {
            orderStatusesRepository.save(
                orderStatusModel.toOrderStatusEntityObjectForSaving()
            )
        } else {
            orderStatusEntity.name = orderStatusModel.status
            orderStatusesRepository.save(orderStatusEntity)
        }
    }

}