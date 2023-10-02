package com.example.myapplication.service

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.model.Payload
import com.example.myapplication.model.UserModel
import com.google.gson.Gson

object SessionManager {
    private const val PREF_NAME = "MyAppPrefs"
    private const val KEY_USER = "user"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUser(user: Payload) {
        val jsonUser = Gson().toJson(user)
        Log.d("SessionManager", "JSON do usuário antes de salvar: $jsonUser")
        sharedPreferences.edit().putString(KEY_USER, jsonUser).apply()
    }

    fun getUser(): Payload? {
        val jsonUser = sharedPreferences.getString(KEY_USER, null)
        Log.d("SessionManager", "JSON do usuário recuperado: $jsonUser")
        return Gson().fromJson(jsonUser, Payload::class.java)
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}