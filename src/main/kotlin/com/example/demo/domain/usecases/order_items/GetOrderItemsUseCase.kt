package com.example.demo.domain.usecases.order_items

import com.example.demo.domain.models.OrderItemModel
import com.example.demo.domain.services.OrderItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class GetOrderItemsUseCase @Autowired constructor(
    private val orderItemsService: OrderItemsService
) {
    operator fun invoke(): List<OrderItemModel>{
        return orderItemsService.getOrderItemsList()
    }
}