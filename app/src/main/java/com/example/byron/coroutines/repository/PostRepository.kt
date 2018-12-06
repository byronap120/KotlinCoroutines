package com.example.byron.coroutines.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.byron.coroutines.data.db.Post
import com.example.byron.coroutines.data.db.PostDao
import com.example.byron.coroutines.data.network.PostService

class PostRepository(private val postDao: PostDao) {

    lateinit var service: PostService

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPosts: LiveData<List<Post>> = postDao.getPosts()

    suspend fun insert(post: Post) {
        postDao.insert(post)
    }

    suspend fun refreshPosts() {
        service = PostService.makeRetrofitService()
        val response = service.getPosts().await()
        if (response.isSuccessful) {
            val dbPost = response.body()?.map {
                Post(
                    it.id,
                    it.userId,
                    it.title,
                    it.body
                )
            }
            dbPost?.let {
                postDao.insertPosts(dbPost)
                Log.d("com.byron.test", it.toString())
            }
        } else {
            Log.d("com.byron.test", "error")
        }
    }
}