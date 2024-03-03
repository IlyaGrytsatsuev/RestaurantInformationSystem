package com.example.demo.domain.services

import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.models.TableModel

interface UsersService {
    fun getUsersList(): List<StuffModel>
    fun createOrUpdateUser(items: List<StuffModel>)
}