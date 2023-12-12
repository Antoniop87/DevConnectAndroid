package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.RegisterRequest
import com.example.myapplication.model.UserModel
import com.example.myapplication.service.ApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername: TextView
    private lateinit var etEmail: TextView
    private lateinit var etPassword: TextView
    private lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()

        btnRegister.setOnClickListener {
            register()
        }
    }

    fun initView(){
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
    }

    fun register() = runBlocking{
        val username = etUsername.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
            launch {
                val call = ApiClient.authService.register(RegisterRequest(username, email, password))
                val response = call
                val i = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        } else {
            Toast.makeText(this@RegisterActivity , "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }



    }

}