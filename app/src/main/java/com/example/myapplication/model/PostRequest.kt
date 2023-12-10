package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName( "content" )
    val content: String,
    @SerializedName( "userId" )
    val userId: Int
)
