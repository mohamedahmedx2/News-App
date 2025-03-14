package com.example.data.repos

import com.example.data.dataSource.search.SearchOnlineDataSource
import com.example.domain.model.News
import com.example.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    val searchOnlineDataSource: SearchOnlineDataSource
) : SearchRepository {
    override suspend fun searchNews(keyword: String): List<News> {
        val resultSearch =
            searchOnlineDataSource.searchNews(keyword).map { newsData -> newsData.toNews() }
                .toList()
        return resultSearch
    }

}