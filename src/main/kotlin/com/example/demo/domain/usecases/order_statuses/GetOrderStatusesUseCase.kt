package com.example.demo.domain.usecases.order_statuses

import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.domain.services.OrderItemsStatusesService
import com.example.demo.domain.services.OrderStatusesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class GetOrderStatusesUseCase @Autowired constructor(
    private val ordersStatusesService: OrderStatusesService
){
    operator fun invoke(): List<OrderStatusModel>{
        return ordersStatusesService.getOrderStatusesList()
    }
}