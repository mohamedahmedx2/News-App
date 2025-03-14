package com.example.domain.repositories

import com.example.domain.model.News

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<News>
}