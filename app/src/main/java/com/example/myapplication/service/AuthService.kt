package com.example.myapplication.service

import com.example.myapplication.model.LoginRequest
import com.example.myapplication.model.Payload
import com.example.myapplication.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/login")
    suspend fun login(@Body credentials: LoginRequest): Payload

    @POST("auth/register")
    suspend fun register(@Body userData: UserModel): UserModel

}