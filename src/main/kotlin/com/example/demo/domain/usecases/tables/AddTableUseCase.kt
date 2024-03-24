package com.example.demo.domain.usecases.tables

import com.example.demo.domain.services.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class AddTableUseCase @Autowired constructor(
    private val tablesService: TablesService
) {
    operator fun invoke() {
        tablesService.createTable()
    }
}