package com.example.byron.coroutines.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface PostDao {

    @Query("SELECT * from post_table")
    fun getPosts(): LiveData<List<Post>>
}