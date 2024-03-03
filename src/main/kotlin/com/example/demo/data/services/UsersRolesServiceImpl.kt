package com.example.demo.data.services

import com.example.demo.data.repository.UserRolesRepository
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.domain.services.UsersRolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UsersRolesServiceImpl @Autowired constructor(
        private val userRolesRepository: UserRolesRepository
) : UsersRolesService{
    override fun getUsersRolesList(): List<UserRoleModel> {
        TODO("Not yet implemented")
    }

    override fun createOrUpdateUserRole(items: List<UserRoleModel>) {
        TODO("Not yet implemented")
    }
}