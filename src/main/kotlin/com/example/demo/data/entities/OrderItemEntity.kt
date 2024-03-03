package com.example.demo.data.entities

import jakarta.persistence.*

@Entity
@Table(name = "orderitems")
class OrderItemEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @ManyToOne
        @JoinColumn(name = "order_id")
        var orderEntity: OrderEntity,
        @ManyToOne
        @JoinColumn(name = "menu_item_id")
        var menuItemEntity: MenuItemEntity,
        @Column(name = "status_name")
        var quantity: Int,
        @ManyToOne
        @JoinColumn(name = "status_id")
        var status: OrderItemStatusEntity,

)