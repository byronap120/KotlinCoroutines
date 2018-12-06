package com.example.byron.coroutines.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.byron.coroutines.db.Post
import com.example.byron.coroutines.db.PostRoomDatabase
import com.example.byron.coroutines.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PostRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPosts: LiveData<List<Post>>

    init {
        val postsDao = PostRoomDatabase.getDatabase(application).postDao()
        repository = PostRepository(postsDao)
        allPosts = repository.allPosts
    }

    fun insert(post: Post) {
        GlobalScope.launch(Dispatchers.Main) {
            repository.insert(post)
        }
    }
}