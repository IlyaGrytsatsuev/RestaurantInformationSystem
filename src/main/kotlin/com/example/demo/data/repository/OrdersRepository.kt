package com.example.demo.data.repository

import com.example.demo.data.entities.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface OrdersRepository: JpaRepository<OrderEntity, Int> {
}