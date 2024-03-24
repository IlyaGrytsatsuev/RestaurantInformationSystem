package com.example.demo.domain.usecases.items_statuses

import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.domain.services.OrderItemsStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteItemsStatusesUseCase @Autowired constructor(
    private val orderItemsStatusesService: OrderItemsStatusesService
) {
    operator fun invoke(statusesIdsList: List<Int>) {
        orderItemsStatusesService.deleteOrderItemStatuses(statusesIdsList)
    }
}