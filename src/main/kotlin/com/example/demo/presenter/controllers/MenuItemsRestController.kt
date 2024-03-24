package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.menu_items.CreateOrUpdateMenuItemsUseCase
import com.example.demo.domain.usecases.menu_items.DeleteMenuItemUseCase
import com.example.demo.domain.usecases.menu_items.GetMenuItemsUseCase
import com.example.demo.presenter.api_models.menu_items.DeleteMenuItemsRequest
import com.example.demo.presenter.mappers.toMenuItemsRequestAndResponse
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.presenter.api_models.ErrorResponseModel
import com.example.demo.presenter.api_models.menu_items.MenuItemsRequestAndResponseModel
import com.example.demo.utils.ByteArrayImageResourceManager
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.NullReceivedException
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.*
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
internal class MenuItemsRestController @Autowired constructor(
        private val getMenuItemsUseCase: GetMenuItemsUseCase,
        private val createOrUpdateMenuItemsUseCase: CreateOrUpdateMenuItemsUseCase,
        private val deleteMenuItemsUseCase: DeleteMenuItemUseCase
) {

    @GetMapping
    fun getMenuItems(): MenuItemsRequestAndResponseModel = getMenuItemsUseCase().toMenuItemsRequestAndResponse()

    @PostMapping("/addOrEditItems")
    fun addOrEditMenuItem(
            @RequestBody menuItems: MenuItemsRequestAndResponseModel,
            bindingResult: BindingResult
    ): ResponseEntity<HttpStatus> {
        if (bindingResult.hasErrors()) {
            throw ValidationException(bindingResult.toMessage())
        }
        createOrUpdateMenuItemsUseCase.invoke(menuItems.menuItemsList)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/deleteItems")
    fun deleteMenuItem(@RequestBody ids: DeleteMenuItemsRequest): ResponseEntity<HttpStatus> {
        if (ids.idsList.isEmpty()) {
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteMenuItemsUseCase(ids.idsList)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/deleteAllItems")
    fun deleteAllMenuItems(): ResponseEntity<HttpStatus> {
        deleteMenuItemsUseCase(emptyList())
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/getImage/{imageName}")
    fun getMenuItemImage(@PathVariable imageName: String): ResponseEntity<ByteArrayResource> {
        val resource = ByteArrayImageResourceManager.getImageByteArrayResource(imageName)
        val headers = HttpHeaders()
        headers.contentType = MediaType.IMAGE_JPEG
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource)
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