package com.example.demo.data.services

import com.example.demo.data.repository.UsersRepository
import com.example.demo.domain.models.StuffModel
import com.example.demo.domain.services.UsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UsersServiceImpl @Autowired constructor(
        private val usersRepository: UsersRepository
): UsersService {
    override fun getUsersList(): List<StuffModel> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createOrUpdateUser(items: List<StuffModel>) {
        TODO("Not yet implemented")
    }
}