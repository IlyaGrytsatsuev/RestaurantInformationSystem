package com.example.demo.data.services

import com.example.demo.data.mappers.toStuffModelsList
import com.example.demo.data.mappers.toEntityObjectForSaving
import com.example.demo.data.repository.TablesRepository
import com.example.demo.domain.models.TableModel
import com.example.demo.domain.services.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TablesServiceImpl @Autowired constructor(
        private val tablesRepository: TablesRepository
) : TablesService {
    override fun getTablesList(): List<TableModel> {
        return tablesRepository.findAll().toStuffModelsList()
    }

    @Transactional
    override fun createTable(items: List<TableModel>) {
        items.forEach { item -> saveNewInstance(item) }
    }

    @Transactional
    override fun deleteTable(item: TableModel) {
        tablesRepository.deleteById(item.id)
    }

    private fun saveNewInstance(tableModel: TableModel) {
        var curSavedTableNumber = 1

        val tablesList = tablesRepository
                .findAll()
        if (tablesList.isNotEmpty()) {
            curSavedTableNumber =
                    tablesList.last().tableNumber + 1
        }

        tablesRepository.save(
                tableModel.toEntityObjectForSaving(
                        curSavedTableNumber
                )
        )
    }

}