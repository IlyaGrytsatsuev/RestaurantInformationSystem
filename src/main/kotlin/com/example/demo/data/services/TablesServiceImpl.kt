package com.example.demo.data.services

import com.example.demo.data.mappers.createTableEntityObjectForSaving
import com.example.demo.data.mappers.toTablesModelsList
import com.example.demo.data.repository.TablesRepository
import com.example.demo.domain.models.TableModel
import com.example.demo.domain.services.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
internal class TablesServiceImpl @Autowired constructor(
    private val tablesRepository: TablesRepository
) : TablesService {
    override fun getTablesList(): List<TableModel> {
        return tablesRepository.findAll().toTablesModelsList()
    }

    @Transactional
    override fun createTable() {
        var curSavedTableNumber = 1

        val tablesList = tablesRepository
            .findAll()
        if (tablesList.isNotEmpty()) {
            curSavedTableNumber =
                tablesList.last().tableNumber + 1
        }

        tablesRepository.save(
            createTableEntityObjectForSaving(
                curSavedTableNumber
            )
        )
    }

    @Transactional
    override fun deleteTables(items: List<Int>) {
        if (items.isEmpty()) {
            tablesRepository.deleteAll()
        } else {
            items.forEach { id ->
                tablesRepository.deleteById(id)
            }
        }
    }

}