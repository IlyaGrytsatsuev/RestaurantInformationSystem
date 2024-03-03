package com.example.demo.data.repository

import com.example.demo.data.entities.TableEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TablesRepository: JpaRepository<TableEntity, Int> {

    fun findByTableNumberOrNull(number: Int): TableEntity?
}