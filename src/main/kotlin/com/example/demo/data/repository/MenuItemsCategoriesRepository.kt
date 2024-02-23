package com.example.demo.data.repository

import com.example.demo.data.entities.MenuItemCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuItemsCategoriesRepository
    : JpaRepository<MenuItemCategoryEntity, Int> {
}