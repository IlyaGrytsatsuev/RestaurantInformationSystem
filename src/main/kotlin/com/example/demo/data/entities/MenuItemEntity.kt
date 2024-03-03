package com.example.demo.data.entities

import jakarta.persistence.*


@Entity
@Table(name = "menu_items")
class MenuItemEntity(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int,
        @Column(name = "name")
        var name: String,
        @ManyToOne
        @JoinColumn(name = "category_id")
        var menuItemCategoryEntity: MenuItemCategoryEntity?,
        @Column(name = "price")
        var price: Int,
        @Column(name = "description")
        var description: String?,
        @Column(name = "img_path")
        var imgPath: String?,
        @OneToMany(
                mappedBy = "menuItemEntity",
                fetch = FetchType.LAZY
        )
        var orderItemsEntitiesList: List<OrderItemEntity>
)
