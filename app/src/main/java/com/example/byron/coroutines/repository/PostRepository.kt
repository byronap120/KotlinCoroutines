package com.example.byron.coroutines.repository

import android.arch.lifecycle.LiveData
import com.example.byron.coroutines.data.db.Post
import com.example.byron.coroutines.data.db.PostDao
import com.example.byron.coroutines.data.network.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private val postDao: PostDao) {

    private lateinit var service: PostService

    val allPosts: LiveData<List<Post>>
        get() = postDao.getPosts()

    suspend fun refreshPosts() {
        withContext(Dispatchers.IO) {
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
                }
            }
        }
    }
}