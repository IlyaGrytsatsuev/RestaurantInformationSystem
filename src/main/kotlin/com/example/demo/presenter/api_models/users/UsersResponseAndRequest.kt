package com.example.demo.presenter.api_models.users

import com.example.demo.domain.models.UserModel

internal data class UsersResponseAndRequest(
    val usersList: List<UserModel>
)
