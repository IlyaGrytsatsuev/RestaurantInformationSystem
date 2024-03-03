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
        @Column(name = "name")
        var name: String,
        @Column(name = "surname")
        var surname: String,
        @ManyToOne
        @JoinColumn(name = "role_id")
        var roleEntity: UserRoleEntity,
        @OneToMany(
                mappedBy = "waiterEntity",
                fetch = FetchType.LAZY)
        var ordersEntitiesList: List<OrderEntity>,
)