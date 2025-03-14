package com.example.data.dataSource.news

import com.example.data.model.NewsData

interface NewsOnlineDataSource {

    suspend fun getNews(sourceId: String): List<NewsData>
}