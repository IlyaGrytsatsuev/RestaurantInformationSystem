package com.example.demo.data.services

import com.example.demo.data.entities.UserRoleEntity
import com.example.demo.data.mappers.toDomainModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.UserRolesRepository
import com.example.demo.domain.models.UserRoleModel
import com.example.demo.domain.services.UsersRolesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UsersRolesServiceImpl @Autowired constructor(
        private val userRolesRepository: UserRolesRepository
) : UsersRolesService {
    override fun getUsersRolesList(): List<UserRoleModel> {
        return userRolesRepository.findAll().toDomainModelsList()
    }

    @Transactional
    override fun createOrUpdateUserRole(items: List<UserRoleModel>) {
        items.forEach { item -> updateUserRoleEntityOrSaveNewInstance(item) }
    }

    @Transactional
    override fun deleteUserRoles(items: List<UserRoleModel>) {
        if(items.isEmpty()){
            userRolesRepository.deleteAll()
        }
        else{
            items.forEach{ item ->
                userRolesRepository.deleteById(item.id)
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
                    userRoleModel.toEntityObjectForSaving()
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