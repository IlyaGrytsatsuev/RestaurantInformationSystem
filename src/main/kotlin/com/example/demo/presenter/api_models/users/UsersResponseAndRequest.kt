package com.example.demo.presenter.api_models.users

import com.example.demo.domain.models.StuffModel

internal data class UsersResponseAndRequest(
    val usersList: List<StuffModel>
)
