package com.example.api.api.dataSource

import com.example.api.api.WebServices
import com.example.data.dataSource.source.SourcesOnlineDataSource
import com.example.data.model.SourceData
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    SourcesOnlineDataSource {
    override suspend fun getSources(categoryId: String): List<SourceData> {
        val response = webServices.getSources(categoryId)
        // تحويل من اللي جاي من ال Api الي اللي انا عايز اروحله في ال Domain
        // conversion by .map{}
        val sources = response.sources?.filterNotNull()?.map { dto -> dto.toSource() }?.toList()
        return sources ?: listOf()
    }
}