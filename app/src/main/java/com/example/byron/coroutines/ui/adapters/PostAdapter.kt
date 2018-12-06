package com.example.byron.coroutines.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.byron.coroutines.R
import com.example.byron.coroutines.data.db.Post


class PostAdapter internal constructor(val context: Context) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var posts = emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val post = posts[position]
        viewHolder.postName.text = post.title
        viewHolder.postDescription.text = post.body
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postName: TextView = itemView.findViewById(R.id.post_name)
        val postDescription: TextView = itemView.findViewById(R.id.post_description)
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

}