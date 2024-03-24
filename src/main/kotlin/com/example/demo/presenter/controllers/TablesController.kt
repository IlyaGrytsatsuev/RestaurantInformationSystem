package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.tables.AddTableUseCase
import com.example.demo.domain.usecases.tables.DeleteTablesUseCase
import com.example.demo.domain.usecases.tables.GetTablesUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.tables.DeleteTablesRequest
import com.example.demo.presenter.api_models.tables.TablesResponseAndRequest
import com.example.demo.presenter.mappers.toTablesResponseAndRequest
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tables")
internal class TablesController @Autowired constructor(
    private val getTablesUseCase: GetTablesUseCase,
    private val addTableUseCase: AddTableUseCase,
    private val deleteTablesUseCase: DeleteTablesUseCase,
) {
    @GetMapping
    fun getTablesList(): TablesResponseAndRequest {
        return getTablesUseCase().toTablesResponseAndRequest()
    }

    @PostMapping("/addTable")
    fun addTable() {
        addTableUseCase()
    }

    @PostMapping("/deleteTable")
    fun deleteTables(@RequestBody deleteTablesRequest: DeleteTablesRequest) {
        if (deleteTablesRequest.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteTablesUseCase(deleteTablesRequest.idsList)
    }

    @PostMapping("/deleteAllTables")
    fun deleteAllStatuses() {
        deleteTablesUseCase(emptyList())
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