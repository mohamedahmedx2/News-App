package com.example.newsapp.api.model.newsResponse

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.newsapp.api.model.sourcesResponse.Source
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date

@Parcelize
data class News(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: Source? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) : Parcelable {
    @SuppressLint("SimpleDateFormat")
    fun getPublishedAtInMillis(): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime: Date? = publishedAt?.let { simpleDateFormat.parse(it) }
        return dateTime?.time
    }
}