package com.example.domain.domain

import com.example.domain.model.News
import com.example.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(val newsRepository: NewsRepository) {
    suspend fun getNews(sourceId: String): List<News> {
        val newsList = newsRepository.getNews(sourceId)
        return newsList
    }
}