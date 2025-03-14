package com.example.domain.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date


class News(
    val title: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
    val description: String? = null,
    val content: String? = null,

    ) {
    @SuppressLint("SimpleDateFormat")
    fun getPublishedAtInMillis(): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime: Date? = publishedAt?.let { simpleDateFormat.parse(it) }
        return dateTime?.time
    }


}