package com.example.data.dataSource.search

import com.example.data.model.NewsData

interface SearchOnlineDataSource {

    suspend fun searchNews(keyword: String): List<NewsData>

}