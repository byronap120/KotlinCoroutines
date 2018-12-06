package com.example.byron.coroutines.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.byron.coroutines.db.Post
import com.example.byron.coroutines.db.PostRoomDatabase
import com.example.byron.coroutines.repository.PostRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    // By default all the coroutines launched in this scope should be using the Main dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

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
        scope.launch(Dispatchers.IO) {
            repository.insert(post)
        }
    }
}