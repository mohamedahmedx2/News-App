package com.example.data.repos

import com.example.data.dataSource.source.SourcesOnlineDataSource
import com.example.domain.model.Source
import com.example.domain.repositories.SourcesRepository
import javax.inject.Inject

class SourcesRepoImpl @Inject constructor(
    val sourceOnlineDataSource: SourcesOnlineDataSource,
    //  val sourceOfflineDataSource: SourcesOfflineDataSource,
) : SourcesRepository {
    override suspend fun getSource(categoryId: String): List<Source> {

        val sources =
            sourceOnlineDataSource.getSources(categoryId).map { source -> source.toSource() }
                .toList()
        //    sourceOfflineDataSource.cacheSources(source)
        return sources
    }
}