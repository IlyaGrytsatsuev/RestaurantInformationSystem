package com.example.demo.domain.usecases.tables

import com.example.demo.domain.services.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class DeleteTablesUseCase @Autowired constructor(
    private val tablesService: TablesService
) {
    operator fun invoke(idsList: List<Int>){
        tablesService.deleteTables(idsList)
    }

}