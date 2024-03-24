package com.example.demo.presenter.api_models.order_items

import com.example.demo.domain.models.OrderItemModel

internal data class OrderItemsResponseAndRequest(
    val orderItemsList: List<OrderItemModel>
)
