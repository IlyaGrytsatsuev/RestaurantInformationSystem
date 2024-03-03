package com.example.demo.data.repository

import com.example.demo.data.entities.OrderStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderStatusesRepository: JpaRepository<OrderStatusEntity, Int> {

    fun findByStatusNameOrNull(statusName: String): OrderStatusEntity?
}