package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.order_statuses.AddOrUpdateOrderStatusesUseCase
import com.example.demo.domain.usecases.order_statuses.DeleteOrderStatusesUseCase
import com.example.demo.domain.usecases.order_statuses.GetOrderStatusesUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.order_statuses.DeleteOrderStatusesRequest
import com.example.demo.presenter.api_models.order_statuses.OrderStatusesResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toOrderStatusesResponseAndRequest
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orderStatuses")
internal class OrderStatusesController @Autowired constructor(
    private val getOrderStatusesUseCase: GetOrderStatusesUseCase,
    private val addOrUpdateOrderStatusesUseCase: AddOrUpdateOrderStatusesUseCase,
    private val deleteOrderStatusesUseCase: DeleteOrderStatusesUseCase
){
    @GetMapping
    fun getStatusesList(): OrderStatusesResponseAndRequest {
        return getOrderStatusesUseCase().toOrderStatusesResponseAndRequest()
    }

    @PostMapping("/addOrEditStatus")
    fun addOrUpdateOrderStatus(
        @RequestBody orderStatusesResponseAndRequest: OrderStatusesResponseAndRequest,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateOrderStatusesUseCase(orderStatusesResponseAndRequest.statusesList)
    }

    @PostMapping("/deleteStatus")
    fun deleteStatus(@RequestBody deleteOrderStatusesRequest: DeleteOrderStatusesRequest){
        if(deleteOrderStatusesRequest.idsList.isEmpty()){
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteOrderStatusesUseCase(deleteOrderStatusesRequest.idsList)
    }

    @PostMapping("/deleteAllStatuses")
    fun deleteAllStatuses(){
        deleteOrderStatusesUseCase(emptyList())
    }

    @ExceptionHandler
    fun handleNullReceivedException(exception: NullReceivedException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(ErrorResponseModel(Constants.NOT_FOUND_RESPONSE_MESSAGE), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleValidationException(exception: ValidationException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(
            ErrorResponseModel(exception.message ?: Constants.VALIDATION_ERROR_MESSAGE),
            HttpStatus.BAD_REQUEST
        )
    }

}