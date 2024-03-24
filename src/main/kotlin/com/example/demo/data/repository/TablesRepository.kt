package com.example.demo.data.repository

import com.example.demo.data.entities.TableEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
internal interface TablesRepository: JpaRepository<TableEntity, Int> {

    @Query("SELECT table from TableEntity table WHERE table.tableNumber = :number")
    fun findByTableNumberOrNull(number: Int): TableEntity?
}