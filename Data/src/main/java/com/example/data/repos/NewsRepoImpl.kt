package com.example.data.repos

import com.example.data.dataSource.news.NewsOnlineDataSource
import com.example.domain.model.News
import com.example.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    // constructor injection
    // لما يكون كلاس محتاج حاجه من كلاس تاني ببعت متغير جوا constructor الكلاس

    val newsOnlineDataSource: NewsOnlineDataSource,
) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<News> {
        val newsList =
            newsOnlineDataSource.getNews(sourceId).map { newsData -> newsData.toNews() }.toList()
        return newsList
    }
}