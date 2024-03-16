package com.example.demo.data.repository

import com.example.demo.data.entities.OrderItemStatusEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderItemsStatusesRepository: JpaRepository<OrderItemStatusEntity, Int>