package com.example.api.model.sourcesResponse

import com.example.data.model.SourceData
import com.google.gson.annotations.SerializedName


data class SourceDTO(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) {
    fun toSource() = SourceData(
        sourceId = id,
        name = name,

        )
}