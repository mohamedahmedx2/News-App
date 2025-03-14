package com.example.data.model

import com.example.domain.model.News


class NewsData(
    val title: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val description: String? = null,
    val content: String? = null,
) {
    fun toNews() = News(
        title = title,
        publishedAt = publishedAt,
        author = author,
        urlToImage = urlToImage,
        url = url,
        description = description,
        content = content,
    )
}