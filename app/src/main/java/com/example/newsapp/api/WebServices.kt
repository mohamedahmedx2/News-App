package com.example.newsapp.api

import com.example.newsapp.model.newsResponse.NewsResponse
import com.example.newsapp.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {


    @GET("v2/top-headlines/sources")

    fun getSources(@Query("category") catId: String): Call<SourcesResponse>

    // https://newsapi.org/v2/everything?apiKey=9cd269ab7b3c4d078bdb32bf7c7680d1&sources=abc-news
    @GET("v2/everything")
    fun getNews(
        @Query("sources") source: String,
    ): Call<NewsResponse>

}