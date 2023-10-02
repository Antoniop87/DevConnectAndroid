package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("payload")
    val payload: PayloadData
)

data class PayloadData(
    @SerializedName("sub")
    val sub: Int,
    @SerializedName("username")
    val username: String
)

