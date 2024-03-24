package com.example.demo.data.entities

import jakarta.persistence.*

@Entity
@Table(name = "order_items_statuses")
internal class OrderItemStatusEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @Column(name = "status")
        var orderItemStatus: String,
        @OneToMany(
                mappedBy = "status",
                fetch = FetchType.LAZY
        )
        var orderItemsList: List<OrderItemEntity>
)