package com.example.data.model

import com.example.domain.model.Source

class SourceData(
    val sourceId: String? = null,
    val name: String? = null,


    ) {
    fun toSource() = Source(
        sourceId = sourceId,
        name = name,
    )
}