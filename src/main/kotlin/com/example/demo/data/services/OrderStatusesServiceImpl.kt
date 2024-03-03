package com.example.demo.data.services

import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.OrderStatusesRepository
import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.domain.services.OrderStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderStatusesServiceImpl @Autowired constructor(
        private val orderStatusesRepository: OrderStatusesRepository
) : OrderStatusesService {

    override fun getOrderStatusesList(): List<OrderStatusModel> {
        return orderStatusesRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createOrUpdateOrderStatuses(items: List<OrderStatusModel>) {
        items.forEach { orderStatusModel ->
            updateOrderStatusEntityOrSaveNewInstance(orderStatusModel)
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
                    orderStatusModel.toEntityObjectForSaving()
            )
        } else {
            orderStatusEntity.name = orderStatusModel.status
        }
    }

}