package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.model.Post

class PostAdapter (private val context: Context, private var postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
   inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

       val tvUsuarioPost: TextView = itemView.findViewById(R.id.tvUsuarioPost)
       val tvTempoPost: TextView = itemView.findViewById(R.id.tvTempoPost)
       val tvPostPost: TextView = itemView.findViewById(R.id.tvPostPost)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.post_list, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]

        holder.tvUsuarioPost.text = post.user.username
        holder.tvPostPost.text = post.content
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun updateData(newPostList: List<Post>) {
        postList = newPostList.reversed()
        notifyDataSetChanged()
    }

}