package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.user_roles.AddOrUpdateUserRolesUseCase
import com.example.demo.domain.usecases.user_roles.DeleteUserRolesUseCase
import com.example.demo.domain.usecases.user_roles.GetUserRolesUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.user_roles.DeleteUserRolesRequest
import com.example.demo.presenter.api_models.user_roles.UserRolesResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toUsersRolesResponseAndRequest
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/userRoles")
internal class UserRolesController @Autowired constructor(
    private val getUserRolesUseCase: GetUserRolesUseCase,
    private val addOrUpdateUserRolesUseCase: AddOrUpdateUserRolesUseCase,
    private val deleteUserRolesUseCase: DeleteUserRolesUseCase
) {

    @GetMapping
    fun getRolesList(): UserRolesResponseAndRequest {
        return getUserRolesUseCase().toUsersRolesResponseAndRequest()
    }

    @PostMapping("/addOrEditRole")
    fun addOrUpdateOrderStatus(
        @RequestBody userRolesResponseAndRequest: UserRolesResponseAndRequest,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateUserRolesUseCase(userRolesResponseAndRequest.rolesList)
    }

    @PostMapping("/deleteRole")
    fun deleteStatus(@RequestBody deleteUserRolesRequest: DeleteUserRolesRequest) {
        if (deleteUserRolesRequest.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteUserRolesUseCase(deleteUserRolesRequest.idsList)
    }

    @PostMapping("/deleteAllRoles")
    fun deleteAllStatuses() {
        deleteUserRolesUseCase(emptyList())
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