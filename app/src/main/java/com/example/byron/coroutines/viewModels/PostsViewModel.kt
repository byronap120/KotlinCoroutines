package com.example.byron.coroutines.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.byron.coroutines.data.db.Post
import com.example.byron.coroutines.data.db.PostRoomDatabase
import com.example.byron.coroutines.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val repository: PostRepository
    private val spinnerValue = MutableLiveData<Boolean>()

    val allPosts: LiveData<List<Post>>
        get() = repository.allPosts

    val spinner: LiveData<Boolean>
        get() = spinnerValue

    init {
        val postsDao = PostRoomDatabase.getDatabase(application).postDao()
        repository = PostRepository(postsDao)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun refreshPosts() {
        uiScope.launch {
            try {
                spinnerValue.value = true
                repository.refreshPosts()
            } catch (error: Error) {
                Log.d("com.byron.test", error.toString())
            } finally {
                spinnerValue.value = false
            }
        }
    }
}