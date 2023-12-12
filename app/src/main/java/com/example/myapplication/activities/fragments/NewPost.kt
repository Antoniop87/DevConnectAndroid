package com.example.myapplication.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.model.PostRequest
import com.example.myapplication.service.ApiClient
import com.example.myapplication.service.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewPost.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewPost : Fragment() {

    private lateinit var etNewPost: TextView
    private lateinit var btnNewPost: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_post, container, false)

        initView(view)

        btnNewPost.setOnClickListener {
            if(etNewPost.text.isNotEmpty()){
                criarPost(etNewPost.text.toString())
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val home = Home()
                fragmentTransaction.replace(R.id.frame_layout, home)

                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            } else {
                Toast.makeText(activity, "Digite algo no seu post!", Toast.LENGTH_SHORT).show()
            }

        }

        return view;
    }

    private fun initView(view: View){
        btnNewPost = view.findViewById(R.id.btn_NewPost)
        etNewPost = view.findViewById(R.id.et_newPost)
    }


    private fun criarPost(content: String) = runBlocking{
        SessionManager.init(requireContext())
        val user = SessionManager.getUser()
        if(user != null){
            val userId = user.payload.sub

            launch(Dispatchers.IO) {
                try {
                    val call = ApiClient.postServcies.criaPost(PostRequest(content, userId))
                    val response = call

                    if (response != null){
                        Toast.makeText(activity, "Post criado", Toast.LENGTH_SHORT).show()
                    }

                    } catch (e: Exception){
                }
            }
        }
    }

}