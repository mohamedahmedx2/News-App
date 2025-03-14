package com.example.domain.repositories

import com.example.domain.model.News


interface SearchRepository {
    suspend fun searchNews(keyword: String): List<News>
}