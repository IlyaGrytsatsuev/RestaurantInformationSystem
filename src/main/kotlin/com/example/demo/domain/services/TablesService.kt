package com.example.demo.domain.services

import com.example.demo.domain.models.TableModel

interface TablesService {
    fun getTablesList(): List<TableModel>
    fun createTable(items: List<TableModel>)
    fun deleteTables(items: List<TableModel>)

}