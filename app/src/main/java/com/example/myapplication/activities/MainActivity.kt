package com.example.myapplication.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.service.SessionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SessionManager.init(this)

        if (SessionManager.isLoggedIn()) {
            Handler().postDelayed(Runnable {
                checkInternetAndNavigateToHome()
            }, 3000)
        } else{
        Handler().postDelayed(Runnable {
            checkInternetAndNavigateToLogin()
        }, 3000)
        }
    }

    private fun checkInternetAndNavigateToHome() {
        if (isInternetAvailable()) {
            val i = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(i)
            finish()
        } else {
            Toast.makeText(this@MainActivity, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInternetAndNavigateToLogin() {
        if (isInternetAvailable()) {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        } else {
            Toast.makeText(this@MainActivity, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}