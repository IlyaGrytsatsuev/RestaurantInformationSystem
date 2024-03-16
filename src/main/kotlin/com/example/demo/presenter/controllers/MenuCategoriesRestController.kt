package com.example.demo.presenter.controllers

import com.example.demo.domain.usecases.menu_items_categories.AddOrEditMenuItemsCategoryUseCase
import com.example.demo.domain.usecases.menu_items_categories.DeleteMenuItemCategoriesUseCase
import com.example.demo.domain.usecases.menu_items_categories.GetMenuItemsCategoriesUseCase
import com.example.demo.presenter.api_models.menu_items.ErrorResponseModel
import com.example.demo.presenter.api_models.menu_items_categories.DeleteMenuItemsCategoriesRequest
import com.example.demo.presenter.api_models.menu_items_categories.MenuItemsCategoriesRequestAndResponse
import com.example.demo.presenter.mappers.toCategoriesRequestAndResponse
import com.example.demo.presenter.mappers.toMessage
import com.example.demo.utils.Constants
import com.example.demo.utils.exceptions.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class MenuCategoriesRestController @Autowired constructor(
        private val getMenuItemsCategoriesUseCase: GetMenuItemsCategoriesUseCase,
        private val addOrEditMenuItemsCategoryUseCase: AddOrEditMenuItemsCategoryUseCase,
        private val deleteMenuItemCategoriesUseCase: DeleteMenuItemCategoriesUseCase
) {

    @GetMapping("/categories")
    fun getMenuItemsCategories(): MenuItemsCategoriesRequestAndResponse =
        getMenuItemsCategoriesUseCase.invoke().toCategoriesRequestAndResponse()

    @PostMapping("/addOrEditCategories")
    fun addOrEditMenuItemCategory(
            @RequestBody categories: MenuItemsCategoriesRequestAndResponse,
            bindingResult: BindingResult
    ): ResponseEntity<HttpStatus>{
        if(bindingResult.hasErrors()){
            throw ValidationException(bindingResult.toMessage())
        }
        addOrEditMenuItemsCategoryUseCase(categories.categoriesList)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/deleteCategories")
    fun deleteMenuItemsCategories(@RequestBody ids: DeleteMenuItemsCategoriesRequest): ResponseEntity<HttpStatus>{
        if(ids.idsList.isEmpty()){
            throw ValidationException(Constants.EMPTY_LIST_MESSAGE)
        }
        deleteMenuItemCategoriesUseCase(ids.idsList)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/deleteAllCategories")
    fun deleteAllMenuItemsCategories(): ResponseEntity<HttpStatus>{
        deleteMenuItemCategoriesUseCase(emptyList())
        return ResponseEntity(HttpStatus.OK)
    }
    @ExceptionHandler
    fun handleValidationException(exception: ValidationException): ResponseEntity<ErrorResponseModel> {
        return ResponseEntity(
                ErrorResponseModel(exception.message ?: Constants.VALIDATION_ERROR_MESSAGE),
                HttpStatus.NOT_FOUND
        )
    }
}