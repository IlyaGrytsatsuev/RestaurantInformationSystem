package com.example.demo.domain.usecases.order_items

import com.example.demo.domain.models.OrderItemModel
import com.example.demo.domain.services.OrderItemsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AddOrUpdateOrderItemUseCase @Autowired constructor(
    private val orderItemsService: OrderItemsService
){
    operator fun invoke(itemsList: List<OrderItemModel>){
        orderItemsService.createOrUpdateOrderItems(itemsList)
    }
}