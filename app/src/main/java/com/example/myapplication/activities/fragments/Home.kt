package com.example.myapplication.activities.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.PostAdapter
import com.example.myapplication.model.Post
import com.example.myapplication.service.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPost)
        postAdapter = PostAdapter(requireContext(), listOf())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = postAdapter

        chamaPosts()

        return view
    }

    fun chamaPosts() = runBlocking {
        val call = ApiClient.postServcies.getPost()

        launch(Dispatchers.IO) {
            try {
                val response = call
                if (response.isSuccessful) {
                    val posts = response.body()

                    if( posts != null){
                        posts?.let {
                            postAdapter.updateData(it)
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Não foi possível carregar os posts", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.d("HomePost", "Erro ao carregar posts")
            }
        }
    }
}
