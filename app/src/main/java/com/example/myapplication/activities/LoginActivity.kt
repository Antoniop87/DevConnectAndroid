package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.model.Payload
import com.example.myapplication.model.UserModel
import com.example.myapplication.service.ApiClient
import com.example.myapplication.service.SessionManager
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        SessionManager.init(this)

        binding.btnLogin.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginUser(username, password)
            } else {
                Toast.makeText(this@LoginActivity, "Preencha os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun loginUser(username: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.authService.login(LoginRequest(username, password))
                if (response != null) {
                    SessionManager.saveUser(response)

                    Log.d("SessionManager", "JSON do usuário recuperado: $response")
                    val i = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login falhou", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@LoginActivity, "Erro de rede", Toast.LENGTH_SHORT).show()

            }
        }
    }


}


