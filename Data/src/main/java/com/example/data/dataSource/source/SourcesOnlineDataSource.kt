package com.example.data.dataSource.source

import com.example.data.model.SourceData

interface SourcesOnlineDataSource {

    suspend fun getSources(categoryId: String): List<SourceData>
}