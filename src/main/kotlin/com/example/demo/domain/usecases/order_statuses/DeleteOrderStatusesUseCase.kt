package com.example.demo.domain.usecases.order_statuses

import com.example.demo.domain.services.OrderStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteOrderStatusesUseCase @Autowired constructor(
    private val orderStatusesService: OrderStatusesService
) {
    operator fun invoke(idsList: List<Int>) {
        orderStatusesService.deleteOrderStatuses(idsList)
    }
}