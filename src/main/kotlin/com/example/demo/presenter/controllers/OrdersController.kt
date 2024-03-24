package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.orders.AddOrUpdateOrders
import com.example.demo.domain.usecases.orders.DeleteOrdersUseCase
import com.example.demo.domain.usecases.orders.GetOrdersUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.orders.DeleteOrdersRequest
import com.example.demo.presenter.api_models.orders.OrdersResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toOrdersResponseAndRequest
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
internal class OrdersController @Autowired constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val addOrUpdateOrders: AddOrUpdateOrders,
    private val deleteOrdersUseCase: DeleteOrdersUseCase,
) {
    @GetMapping
    fun getStatusesList(): OrdersResponseAndRequest {
        return getOrdersUseCase().toOrdersResponseAndRequest()
    }

    @PostMapping("/addOrEditOrder")
    fun addOrUpdateOrder(
        @RequestBody ordersResponseAndRequest: OrdersResponseAndRequest,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateOrders(ordersResponseAndRequest.ordersList)
    }

    @PostMapping("/deleteOrders")
    fun deleteStatus(@RequestBody deleteOrdersRequest: DeleteOrdersRequest) {
        if (deleteOrdersRequest.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteOrdersUseCase(deleteOrdersRequest.idsList)
    }

    @PostMapping("/deleteAllOrders")
    fun deleteAllStatuses() {
        deleteOrdersUseCase(emptyList())
    }

    @ExceptionHandler
    fun handleNullReceivedException(exception: NullReceivedException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(ErrorResponseModel(Constants.NOT_FOUND_RESPONSE_MESSAGE), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleValidationException(exception: ValidationException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(
            ErrorResponseModel(exception.message ?: Constants.VALIDATION_ERROR_MESSAGE),
            HttpStatus.NOT_FOUND
        )
    }


}