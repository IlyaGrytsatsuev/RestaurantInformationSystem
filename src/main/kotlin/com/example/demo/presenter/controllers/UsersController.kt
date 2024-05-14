package com.example.demo.presenter.controllers

import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.domain.usecases.users.AddOrUpdateUsersUseCase
import com.example.demo.domain.usecases.users.AuthorizeUserUseCase
import com.example.demo.domain.usecases.users.DeleteUsersUseCase
import com.example.demo.domain.usecases.users.GetUsersUseCase
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.users.AuthorizationUserRequestAndResponse
import com.example.demo.presenter.api_models.users.DeleteUsersRequest
import com.example.demo.presenter.api_models.users.UsersResponseAndRequest
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.mappers.toUsersAuthorizationRequestAndResponse
import com.example.demo.presenter.mappers.toUsersResponseAndRequest
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
    private val authorizeUserUseCase: AuthorizeUserUseCase
) {

    @GetMapping
    fun getUsersList(): UsersResponseAndRequest {
        return getUsersUseCase().toUsersResponseAndRequest()
    }

    @PostMapping("/authorize")
    fun authorizeUser(
        @RequestBody authorizationModel: UserAuthorizationModel,
        bindingResult: BindingResult
    ): AuthorizationUserRequestAndResponse {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        return authorizeUserUseCase
            .invoke(authorizationModel)
            .toUsersAuthorizationRequestAndResponse()
    }

    @PostMapping("/addOrEditUser")
    fun addOrUpdateUser(
        @RequestBody addOrUpdateUserRequest: AuthorizationUserRequestAndResponse,
        bindingResult: BindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        addOrUpdateUsersUseCase(addOrUpdateUserRequest.userAuthList)
    }

    @PostMapping("/deleteUser")
    fun deleteUser(@RequestBody deleteUsersRequest: DeleteUsersRequest) {
        if (deleteUsersRequest.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteUsersUseCase(deleteUsersRequest.idsList)
    }

    @PostMapping("/deleteAllRoles")
    fun deleteAllUsers() {
        deleteUsersUseCase(emptyList())
    }

    @ExceptionHandler
    fun handleNullReceivedException(exception: NullReceivedException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(ErrorResponseModel(Constants.NOT_FOUND_RESPONSE_MESSAGE), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleValidationException(exception: ValidationException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(
            ErrorResponseModel(exception.message ?: Constants.VALIDATION_ERROR_MESSAGE),
            HttpStatus.BAD_REQUEST
        )
    }
}