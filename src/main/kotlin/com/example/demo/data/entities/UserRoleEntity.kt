package com.example.demo.data.entities

import jakarta.persistence.*


@Entity
@Table(name ="user_roles")
class UserRoleEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @Column(name = "role_name")
        var roleName: String,
        @OneToMany(
                mappedBy = "roleEntity",
                fetch = FetchType.LAZY)
        var usersEntitiesList: List<UserEntity>
)