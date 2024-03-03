package com.example.demo

import com.example.demo.data.services.MenuItemsServiceImpl
import com.example.demo.domain.services.MenuItemsService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
