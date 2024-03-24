package com.example.demo.data.repository

import com.example.demo.data.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface UsersRepository: JpaRepository<UserEntity, Int> {
}