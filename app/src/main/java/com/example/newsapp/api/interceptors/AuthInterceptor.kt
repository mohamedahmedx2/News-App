package com.example.newsapp.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = "9cd269ab7b3c4d078bdb32bf7c7680d1"
        val newRequestBuilder = chain.request().newBuilder()
        newRequestBuilder.addHeader("Authorization", apiKey)
        return chain.proceed(newRequestBuilder.build())
    }
}