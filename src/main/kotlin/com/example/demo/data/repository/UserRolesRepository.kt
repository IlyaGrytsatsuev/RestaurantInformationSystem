package com.example.demo.data.repository

import com.example.demo.data.entities.UserRoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface UserRolesRepository: JpaRepository<UserRoleEntity, Int> {
}