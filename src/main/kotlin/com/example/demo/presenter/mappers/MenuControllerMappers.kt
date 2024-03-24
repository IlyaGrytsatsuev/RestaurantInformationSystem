package com.example.demo.presenter.mappers

import com.example.demo.domain.models.*
import com.example.demo.domain.models.MenuItemCategoryModel
import com.example.demo.domain.models.MenuItemModel
import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.domain.models.OrderStatusModel
import com.example.demo.presenter.api_models.tables.TablesResponseAndRequest
import com.example.demo.presenter.api_models.order_items.OrderItemsResponseAndRequest
import com.example.demo.presenter.api_models.items_statuses.ItemsStatusesRequestAndResponse
import com.example.demo.presenter.api_models.menu_items.MenuItemsRequestAndResponseModel
import com.example.demo.presenter.api_models.menu_items_categories.MenuItemsCategoriesRequestAndResponse
import com.example.demo.presenter.api_models.order_statuses.OrderStatusesResponseAndRequest
import com.example.demo.presenter.api_models.orders.OrdersResponseAndRequest
import com.example.demo.presenter.api_models.user_roles.UserRolesResponseAndRequest
import com.example.demo.presenter.api_models.users.UsersResponseAndRequest

internal fun List<MenuItemModel>.toMenuItemsRequestAndResponse(): MenuItemsRequestAndResponseModel =
        MenuItemsRequestAndResponseModel(this)
internal fun List<MenuItemCategoryModel>.toCategoriesRequestAndResponse(): MenuItemsCategoriesRequestAndResponse =
        MenuItemsCategoriesRequestAndResponse(this)
internal fun List<OrderItemStatusModel>.toItemsStatusesResponseAndRequest(): ItemsStatusesRequestAndResponse =
        ItemsStatusesRequestAndResponse(this)
internal fun List<OrderStatusModel>.toOrderStatusesResponseAndRequest(): OrderStatusesResponseAndRequest =
        OrderStatusesResponseAndRequest(this)
internal fun List<OrderItemModel>.toOrderItemsResponseAndRequest(): OrderItemsResponseAndRequest =
        OrderItemsResponseAndRequest(this)
internal fun List<OrderModel>.toOrdersResponseAndRequest(): OrdersResponseAndRequest =
        OrdersResponseAndRequest(this)
internal fun List<TableModel>.toTablesResponseAndRequest(): TablesResponseAndRequest =
        TablesResponseAndRequest(this)
internal fun List<UserRoleModel>.toUsersRolesResponseAndRequest(): UserRolesResponseAndRequest =
        UserRolesResponseAndRequest(this)
internal fun List<StuffModel>.toUsersResponseAndRequest(): UsersResponseAndRequest =
        UsersResponseAndRequest(this)

