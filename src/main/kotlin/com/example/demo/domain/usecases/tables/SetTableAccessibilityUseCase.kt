package com.example.demo.domain.usecases.tables

import com.example.demo.domain.models.TableModel
import com.example.demo.domain.services.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class SetTableAccessibilityUseCase @Autowired constructor(
    private val tablesService: TablesService
) {
    operator fun invoke(table: TableModel){
        tablesService.setTableAccessibility(table)
    }
}