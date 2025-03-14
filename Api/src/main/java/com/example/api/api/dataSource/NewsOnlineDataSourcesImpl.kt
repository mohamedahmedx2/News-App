package com.example.api.api.dataSource

import android.annotation.SuppressLint
import com.example.api.api.WebServices
import com.example.data.dataSource.news.NewsOnlineDataSource
import com.example.data.model.NewsData
import javax.inject.Inject

class NewsOnlineDataSourcesImpl @Inject constructor(private val webServices: WebServices) :
    NewsOnlineDataSource {
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getNews(sourceId: String): List<NewsData> {
        val response = webServices.getNews(sourceId)
        val newsList = response.newsList?.filterNotNull()?.map { dto -> dto.toNews() }?.toList()
        return newsList ?: listOf()
    }
}