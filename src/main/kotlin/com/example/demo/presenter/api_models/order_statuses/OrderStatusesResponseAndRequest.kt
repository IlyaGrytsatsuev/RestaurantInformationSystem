package com.example.demo.presenter.api_models.order_statuses

import com.example.demo.domain.models.OrderStatusModel

internal data class OrderStatusesResponseAndRequest(
    val statusesList: List<OrderStatusModel>
)
