package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password")val password: String,
)
