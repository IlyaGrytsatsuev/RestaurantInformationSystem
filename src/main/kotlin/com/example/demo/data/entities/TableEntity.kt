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
        @Column(name = "is_free")
        var isFree: Boolean,
        @OneToMany(
                mappedBy = "tableEntity",
                fetch = FetchType.LAZY)
        var orderEntitiesList: List<OrderEntity>,
)