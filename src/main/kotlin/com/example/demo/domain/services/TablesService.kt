package com.example.demo.domain.services

import com.example.demo.domain.models.TableModel

internal interface TablesService {
    fun getTablesList(): List<TableModel>
    fun createTable()
    fun deleteTables(items: List<Int>)

}