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
    private const val KEY_TOKEN = "access_token"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
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

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

}