package com.example.byron.coroutines.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.byron.coroutines.db.Post
import com.example.byron.coroutines.db.PostDao

class PostRepository(private val postDao: PostDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPosts: LiveData<List<Post>> = postDao.getPosts()

    @WorkerThread
    suspend fun insert(post: Post) {
        postDao.insert(post)
    }
}