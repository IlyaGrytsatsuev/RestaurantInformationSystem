package com.example.demo.domain.usecases.orders

import com.example.demo.domain.models.OrderModel
import com.example.demo.domain.services.OrdersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AddOrUpdateOrders @Autowired constructor(
    private val ordersService: OrdersService
) {
    operator fun invoke(orders: List<OrderModel>){
        ordersService.createOrUpdateOrder(orders)
    }
}