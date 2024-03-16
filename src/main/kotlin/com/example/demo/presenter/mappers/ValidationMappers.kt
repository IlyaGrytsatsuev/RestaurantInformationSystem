package com.example.demo.presenter.mappers

import org.springframework.validation.BindingResult

fun BindingResult.toMessage(): String{
    return this.fieldErrors.joinToString { error ->
        error.field + " - "
    } + " fields errors"
}