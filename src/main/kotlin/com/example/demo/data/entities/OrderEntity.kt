package com.example.demo.data.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OderEntity(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,
        @OneToOne
        @JoinColumn(name = "table_id")
        var tableEntity: TableEntity,
        //TODO waiterEntity
        @ManyToOne
        @JoinColumn(name = "id")
        var orderStatusEntity: OrderStatusEntity,
        @Column(name = "timestamp")
        var dateTime: LocalDateTime
)