package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")val id: Int?,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password")val password: String,
)
