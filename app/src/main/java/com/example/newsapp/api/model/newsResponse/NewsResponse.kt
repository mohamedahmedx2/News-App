package com.example.newsapp.api.model.newsResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val newsList: List<News?>? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable