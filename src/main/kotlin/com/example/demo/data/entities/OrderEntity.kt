package com.example.demo.data.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,
        @ManyToOne
        @JoinColumn(name = "table_id")
        var tableEntity: TableEntity,
        @ManyToOne
        @JoinColumn(name = "waiter_id")
        var waiterEntity: UserEntity?,
        @ManyToOne
        @JoinColumn(name = "id")
        var orderStatusEntity: OrderStatusEntity,
        @Column(name = "timestamp")
        var dateTime: LocalDateTime,
        @OneToMany(mappedBy = "orderItemEntity")
        var orderItemsEntitiesList: List<OrderItemEntity>?
)