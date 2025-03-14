package com.example.newsapp.handel

data class ErrorState(
    val errorMessenger: String? = null,
    val onRetry: (() -> Unit)? = null
)