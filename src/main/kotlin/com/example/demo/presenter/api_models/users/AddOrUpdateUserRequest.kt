package com.example.demo.presenter.api_models.users

import com.example.demo.domain.models.UserAuthorizationModel

internal data class AddOrUpdateUserRequest(
    val userAuthList: List<UserAuthorizationModel>
)
