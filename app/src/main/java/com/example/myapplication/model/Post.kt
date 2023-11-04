package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName( "postId" )
    val postId: Int,
    @SerializedName( "content" )
    val content: String,
    @SerializedName( "userId" )
    val userId: Int,
    @SerializedName( "likesCount" )
    val likesCount: Int
)
