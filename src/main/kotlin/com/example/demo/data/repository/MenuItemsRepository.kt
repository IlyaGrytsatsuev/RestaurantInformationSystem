package com.example.demo.data.repository

import com.example.demo.data.entities.MenuItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface MenuItemsRepository: JpaRepository<MenuItemEntity, Int> {


}