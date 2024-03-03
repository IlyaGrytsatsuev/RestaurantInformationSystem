package com.example.demo.data.repository

import com.example.demo.data.entities.OrderItemStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemsStatusesRepository: JpaRepository<OrderItemStatusEntity, Int> {

    fun findByStatusOrNull(status: String): OrderItemStatusEntity?
}