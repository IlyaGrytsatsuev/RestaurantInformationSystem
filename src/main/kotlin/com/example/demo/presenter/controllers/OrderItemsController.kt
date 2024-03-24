package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.order_items.AddOrUpdateOrderItemUseCase
import com.example.demo.domain.usecases.order_items.DeleteOrderItemsUseCase
import com.example.demo.domain.usecases.order_items.GetOrderItemsUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.order_items.DeleteOrderItemsRequest
import com.example.demo.presenter.api_models.order_items.OrderItemsResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toOrderItemsResponseAndRequest
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orderItems")
internal class OrderItemsController @Autowired constructor(
    private val getOrderItemsUseCase: GetOrderItemsUseCase,
    private val addOrUpdateOrderItemUseCase: AddOrUpdateOrderItemUseCase,
    private val deleteOrderItemsUseCase: DeleteOrderItemsUseCase,
) {

    @GetMapping
    fun getOrderItemsList(): OrderItemsResponseAndRequest {
        return getOrderItemsUseCase().toOrderItemsResponseAndRequest()
    }

    @PostMapping("/addOrEditItem")
    fun addOrUpdateOrderItemsStatus(
        @RequestBody orderItemsResponseAndRequest: OrderItemsResponseAndRequest,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateOrderItemUseCase(orderItemsResponseAndRequest.orderItemsList)
    }

    @PostMapping("/deleteItem")
    fun deleteItem(@RequestBody deleteOrderItemsRequest: DeleteOrderItemsRequest){
        if(deleteOrderItemsRequest.idsList.isEmpty()){
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteOrderItemsUseCase(deleteOrderItemsRequest.idsList)
    }

    @PostMapping("/deleteAllItems")
    fun deleteAllStatuses(){
        deleteOrderItemsUseCase(emptyList())
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