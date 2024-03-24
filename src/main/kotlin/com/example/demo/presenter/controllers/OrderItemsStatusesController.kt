package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.items_statuses.AddOrUpdateItemsStatusesUseCase
import com.example.demo.domain.usecases.items_statuses.DeleteItemsStatusesUseCase
import com.example.demo.domain.usecases.items_statuses.GetItemsStatusesUseCase
import com.example.demo.presenter.api_models.items_statuses.ItemsStatusesRequestAndResponse
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.items_statuses.DeleteItemsStatusesListRequest
import com.example.demo.presenter.mappers.toItemsStatusesResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/itemsStatuses")
internal class OrderItemsStatusesController @Autowired constructor(
    private val getItemsStatusesUseCase: GetItemsStatusesUseCase,
    private val addOrUpdateItemsStatusesUseCase: AddOrUpdateItemsStatusesUseCase,
    private val deleteItemsStatusesUseCase: DeleteItemsStatusesUseCase,
) {

    @GetMapping
    fun getStatusesList(): ItemsStatusesRequestAndResponse {
        return getItemsStatusesUseCase().toItemsStatusesResponseAndRequest()
    }

    @PostMapping("/addOrEditStatus")
    fun addOrUpdateItemStatus(
        @RequestBody itemsStatusesRequestAndResponse: ItemsStatusesRequestAndResponse,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateItemsStatusesUseCase(itemsStatusesRequestAndResponse.statusesList)
    }

    @PostMapping("/deleteStatus")
    fun deleteStatus(@RequestBody deleteItemsStatusesListRequest: DeleteItemsStatusesListRequest){
        if(deleteItemsStatusesListRequest.idsList.isEmpty()){
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteItemsStatusesUseCase(deleteItemsStatusesListRequest.idsList)
    }

    @PostMapping("/deleteAllStatuses")
    fun deleteAllStatuses(){
        deleteItemsStatusesUseCase(emptyList())
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