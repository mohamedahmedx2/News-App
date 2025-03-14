package com.example.api.api.dataSource

import com.example.api.api.WebServices
import com.example.data.dataSource.search.SearchOnlineDataSource
import com.example.data.model.NewsData
import javax.inject.Inject

class SearchOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    SearchOnlineDataSource {
    override suspend fun searchNews(keyword: String): List<NewsData> {
        val response = webServices.getSearchNews(keyword)
        val newsList = response.newsList?.filterNotNull()?.map { dto -> dto.toNews() }?.toList()
        return newsList ?: listOf()
    }
}
///?.filterNotNull()?.map { dto -> dto.toNews() }?.toList()