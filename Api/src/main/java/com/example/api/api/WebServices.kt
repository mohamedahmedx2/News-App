package com.example.api.api


import com.example.api.model.newsResponse.NewsResponse
import com.example.api.model.sourcesResponse.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("v2/top-headlines/sources")

    suspend fun getSources(@Query("category") catId: String): SourcesResponse

    // https://newsapi.org/v2/everything?apiKey=9cd269ab7b3c4d078bdb32bf7c7680d1&sources=abc-news
    @GET("v2/everything")
    suspend fun getNews(
        @Query("sources") source: String,
    ): NewsResponse

    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q") query: String,
    ): NewsResponse

}