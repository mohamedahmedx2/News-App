package com.example.api.model

import com.google.gson.annotations.SerializedName


data class ErrorResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null,
)