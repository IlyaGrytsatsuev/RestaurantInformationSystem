package com.example.demo.domain.usecases.order_items

import com.example.demo.domain.services.OrderItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteOrderItemsUseCase @Autowired constructor(
    private val orderItemsService: OrderItemsService
){
    operator fun invoke(idsList: List<Int>){
        orderItemsService.deleteOrderItems(idsList)
    }
}