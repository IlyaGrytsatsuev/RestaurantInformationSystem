package com.example.demo.data.repository

import com.example.demo.data.entities.MenuItemCategoryEntity
import com.example.demo.data.entities.MenuItemEntity
import com.example.demo.data.entities.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
internal interface OrderItemsRepository
    : JpaRepository<OrderItemEntity, Int> {

}