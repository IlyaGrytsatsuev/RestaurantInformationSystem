package com.example.demo.domain.usecases.items_statuses

import com.example.demo.domain.models.OrderItemStatusModel
import com.example.demo.domain.services.OrderItemsStatusesService
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

@Component
internal class GetItemsStatusesUseCase @Autowired constructor(
    private val orderItemsStatusesService: OrderItemsStatusesService
) {
    operator fun invoke(): List<OrderItemStatusModel> {
        return orderItemsStatusesService.getOrderItemsStatusesList()
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