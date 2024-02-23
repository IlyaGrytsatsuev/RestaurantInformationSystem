package com.example.demo.data.entities

import jakarta.persistence.*

@Entity
@Table(name ="users")
class UserEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @Column(name = "username")
        var username: String,
        @Column(name = "password")
        var password: String,
        @ManyToOne
        @JoinColumn(name = "role_id")
        var roleEntity: UserRoleEntity?,
        @OneToMany(mappedBy = "waiterEntity")
        var ordersEntitiesList: List<OrderEntity>?,
)