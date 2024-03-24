package com.example.demo.data.services

import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.data.mappers.toUserRoleDomainModelsList
import com.example.demo.data.mappers.toUserRoleEntityObjectForSaving
import com.example.demo.data.repository.UserRolesRepository
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.domain.services.UsersRolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class UsersRolesServiceImpl @Autowired constructor(
    private val userRolesRepository: UserRolesRepository
) : UsersRolesService {
    override fun getUsersRolesList(): List<UserRoleModel> {
        return userRolesRepository.findAll().toUserRoleDomainModelsList()
    }

    @Transactional
    override fun createOrUpdateUserRole(items: List<UserRoleModel>) {
        items.forEach { item -> updateUserRoleEntityOrSaveNewInstance(item) }
    }

    @Transactional
    override fun deleteUserRoles(items: List<Int>) {
        if (items.isEmpty()) {
            userRolesRepository.deleteAll()
        } else {
            items.forEach { id ->
                userRolesRepository.deleteById(id)
            }
        }
    }

    private fun updateUserRoleEntityOrSaveNewInstance(
        userRoleModel: UserRoleModel
    ) {
        val userRoleEntity = userRolesRepository
            .findByIdOrNull(userRoleModel.id)

        if (userRoleEntity == null) {
            userRolesRepository.save(
                userRoleModel.toUserRoleEntityObjectForSaving()
            )
        } else {
            userRoleEntity.setEntityProperties(userRoleModel)
        }
    }

    private fun UserRoleEntity.setEntityProperties(
        userRoleModel: UserRoleModel
    ) {
        this.roleName = userRoleModel.roleName
    }
}