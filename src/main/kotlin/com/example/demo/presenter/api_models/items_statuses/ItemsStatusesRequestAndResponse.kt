package com.example.demo.presenter.api_models.items_statuses

import com.example.demo.domain.models.OrderItemStatusModel

internal data class ItemsStatusesRequestAndResponse(
    val statusesList: List<OrderItemStatusModel>
)