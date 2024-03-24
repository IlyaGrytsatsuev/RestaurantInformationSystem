package com.example.demo.presenter.api_models.orders

import com.example.demo.domain.models.OrderModel

internal data class OrdersResponseAndRequest (
    val ordersList: List<OrderModel>
)