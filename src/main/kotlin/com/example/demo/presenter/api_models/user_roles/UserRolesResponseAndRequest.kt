package com.example.demo.presenter.api_models.user_roles

import com.example.demo.domain.models.UserRoleModel

internal data class UserRolesResponseAndRequest (
    val rolesList: List<UserRoleModel>
)