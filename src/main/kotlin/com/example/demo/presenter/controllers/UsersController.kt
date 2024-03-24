package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.user_roles.DeleteUserRolesUseCase
import com.example.demo.domain.usecases.users.AddOrUpdateUsersUseCase
import com.example.demo.domain.usecases.users.DeleteUsersUseCase
import com.example.demo.domain.usecases.users.GetUsersUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.user_roles.DeleteUserRolesRequest
import com.example.demo.presenter.api_models.user_roles.UserRolesResponseAndRequest
import com.example.demo.presenter.api_models.users.AddOrUpdateUserRequest
import com.example.demo.presenter.api_models.users.DeleteUsersRequest
import com.example.demo.presenter.api_models.users.UsersResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toUsersResponseAndRequest
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
@RequestMapping("/users")
internal class UsersController @Autowired constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val addOrUpdateUsersUseCase: AddOrUpdateUsersUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase,
) {

    @GetMapping
    fun getUsersList(): UsersResponseAndRequest {
        return getUsersUseCase().toUsersResponseAndRequest()
    }

    @PostMapping("/addOrEditUser")
    fun addOrUpdateOrderStatus(
        @RequestBody addOrUpdateUserRequest: AddOrUpdateUserRequest,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateUsersUseCase(addOrUpdateUserRequest.userAuthList)
    }

    @PostMapping("/deleteUser")
    fun deleteStatus(@RequestBody deleteUsersRequest: DeleteUsersRequest) {
        if (deleteUsersRequest.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteUsersUseCase(deleteUsersRequest.idsList)
    }

    @PostMapping("/deleteAllRoles")
    fun deleteAllStatuses() {
        deleteUsersUseCase(emptyList())
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