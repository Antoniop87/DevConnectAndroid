package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.model.UserModel
import com.example.myapplication.service.SessionManager

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        SessionManager.init(this)

        val user = SessionManager.getUser()
        //ver usuario logado
        if (user != null) {
            val userName = user.payload.username

            val userNameTextView = binding.usernametv

            userNameTextView.text = "Olá, $userName"
        }
    }
}