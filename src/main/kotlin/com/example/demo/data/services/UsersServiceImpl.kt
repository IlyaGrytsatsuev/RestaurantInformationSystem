package com.example.demo.data.services

import com.example.demo.data.entities.UserEntity
import com.example.demo.data.mappers.toStuffModel
import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toUserAuthorizationModel
import com.example.demo.data.mappers.toUserEntityObjectForSaving
import com.example.demo.data.repository.UserRolesRepository
import com.example.demo.data.repository.UsersRepository
import com.example.demo.domain.models.UserModel
import com.example.demo.domain.models.UserAuthorizationModel
import com.example.demo.domain.services.UsersService
import com.example.demo.utils.exceptions.NullReceivedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class UsersServiceImpl @Autowired constructor(
    private val usersRepository: UsersRepository,
    private val userRolesRepository: UserRolesRepository
) : UsersService {
    override fun getUsersList(): List<UserModel> {
        return usersRepository.findAll().toStuffModelsList()
    }

    override fun authorizeUser(user: UserAuthorizationModel): List<UserAuthorizationModel> {
        val userEntity = usersRepository.findUserEntityByUsername(user.username)?: throw NullReceivedException()
        if(userEntity.password == user.password) {
            return listOf(userEntity.toUserAuthorizationModel())
        }
        throw NullReceivedException()
    }

    @Transactional
    override fun createOrUpdateUser(items: List<UserAuthorizationModel>) {
        items.forEach { item -> updateUserEntityOrSaveNewInstance(item) }
    }

    @Transactional
    override fun deleteUsers(items: List<Int>) {
        if (items.isEmpty()) {
            usersRepository.deleteAll()
        } else {
            items.forEach { id ->
                usersRepository.deleteById(id)
            }
        }
    }

    private fun updateUserEntityOrSaveNewInstance(
        userAuthorizationModel: UserAuthorizationModel
    ) {
        val userEntity = usersRepository
            .findByIdOrNull(userAuthorizationModel.id)

        if (userEntity == null) {
            val userRoleEntity = userRolesRepository.findByIdOrNull(
                userAuthorizationModel.roleId
            )
            usersRepository.save(
                userAuthorizationModel.toUserEntityObjectForSaving(
                    userRoleEntity
                )
            )
        } else {
            userEntity.setEntityProperties(userAuthorizationModel)
            usersRepository.save(userEntity)
        }
    }

    private fun UserEntity.setEntityProperties(
        userAuthorizationModel: UserAuthorizationModel
    ) {
        this.username = userAuthorizationModel.username
        this.password = userAuthorizationModel.password
        this.name = userAuthorizationModel.name
        this.surname = userAuthorizationModel.surname
        this.roleEntity = userRolesRepository.findByIdOrNull(
            userAuthorizationModel.roleId
        ) ?: throw NullReceivedException()
    }
}