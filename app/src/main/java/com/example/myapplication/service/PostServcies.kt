package com.example.myapplication.service

import com.example.myapplication.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostServcies {

    @GET("api/posts")
    suspend fun getPost(): Response<List<Post>>

    @POST("api/posts")
    suspend fun criaPost(@Body postData: Post): Post

}