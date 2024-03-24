package com.example.demo.presenter.api_models.tables

import com.example.demo.domain.models.TableModel

internal data class TablesResponseAndRequest(
    val tablesList: List<TableModel>
)
