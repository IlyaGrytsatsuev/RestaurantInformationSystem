package com.example.demo.data.services

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class ResetPrimaryKeysSequencesService @Autowired constructor(
        private val entityManager: EntityManager
) {


    @Transactional
    fun resetSequences() {
        val tablesList = entityManager.createNativeQuery(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'"
        ).resultList as List<String>
        tablesList.forEach { tableName ->
            val seqName = "${tableName}_id_seq"
            val query = entityManager
                    .createNativeQuery("ALTER SEQUENCE " + seqName + " RESTART WITH 1")
            query.executeUpdate()

        }
    }
}