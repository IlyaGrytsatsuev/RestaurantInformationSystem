package com.example.demo.data.services

import com.example.demo.data.repository.MenuItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuItemsService @Autowired constructor(
        private val menuItemRepository: MenuItemRepository
) {
    fun getMenuItems(){
        menuItemRepository.findAll()
    }
}