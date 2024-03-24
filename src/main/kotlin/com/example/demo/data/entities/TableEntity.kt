package com.example.demo.data.entities

import jakarta.persistence.*

@Entity
@Table(name = "tables")
internal class TableEntity (
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,
        @Column(name = "table_number")
        var tableNumber: Int,
        @OneToMany(
                mappedBy = "tableEntity",
                fetch = FetchType.LAZY)
        var orderEntitiesList: List<OrderEntity>,
)