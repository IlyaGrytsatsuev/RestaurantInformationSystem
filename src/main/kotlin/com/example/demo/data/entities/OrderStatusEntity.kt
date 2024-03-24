package com.example.demo.data.entities

import jakarta.persistence.*


@Entity
@Table(name = "order_statuses")
internal class OrderStatusEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @Column(name = "status_name")
        var name: String,
        @OneToMany(
                mappedBy = "orderStatusEntity",
                fetch = FetchType.LAZY
        )
        var ordersEntitiesList: List<OrderEntity>,
)