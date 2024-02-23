package com.example.demo.data.entities

import jakarta.persistence.*

@Entity
@Table(name ="menu_items_categories")
class MenuItemCategoryEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int,
        @Column(name = "name")
        var name: String,
        @OneToMany(mappedBy = "menuItemCategoryEntity")
        var menuItemsEntitiesList: List<MenuItemEntity>,
)