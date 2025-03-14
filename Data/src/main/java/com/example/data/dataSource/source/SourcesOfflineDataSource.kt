package com.example.data.dataSource.source

import com.example.data.model.SourceData

interface SourcesOfflineDataSource {

    suspend fun getSources(categoryId: String): List<SourceData>
    suspend fun cacheSources(sourceList: List<SourceData>)
}