package com.example.domain.domain

import com.example.domain.model.News
import com.example.domain.repositories.SearchRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(val searchRepository: SearchRepository) {
    suspend fun searchNews(keyword: String): List<News> {
        val resultSearch = searchRepository.searchNews(keyword)
        return resultSearch
    }
}