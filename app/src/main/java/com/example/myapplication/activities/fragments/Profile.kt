package com.example.myapplication.activities.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myapplication.activities.SettingsActivity
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.service.SessionManager

class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root = binding.root

        SessionManager.init(requireContext())

        configuraProfile()
        abreConfiguracaoActivity()
        return root
    }

    fun configuraProfile(){
        val user = SessionManager.getUser()
        if (user != null) {
            val userName = user.payload.username

            binding.usernametv.text = "@$userName"
            Log.d("User", "Username: $userName")
        }
    }

    fun abreConfiguracaoActivity(){
        val btnConfiguracoes: ImageView = binding.btnConfiguracoes
        btnConfiguracoes.setOnClickListener {
            val i = Intent(activity, SettingsActivity::class.java)
            startActivity(i)
        }
    }
}