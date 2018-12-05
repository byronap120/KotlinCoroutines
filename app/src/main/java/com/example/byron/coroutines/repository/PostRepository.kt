package com.example.byron.coroutines.repository

import android.arch.lifecycle.LiveData
import com.example.byron.coroutines.db.Post
import com.example.byron.coroutines.db.PostDao

class PostRepository(private val postDao: PostDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPosts: LiveData<List<Post>> = postDao.getPosts()
}