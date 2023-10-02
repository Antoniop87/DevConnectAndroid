package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName( "username" )
    var username: String,

    @SerializedName ( "password" )
    var password: String
)