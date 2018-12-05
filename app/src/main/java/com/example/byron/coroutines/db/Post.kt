package com.example.byron.coroutines.db

import android.arch.persistence.room.Entity

@Entity(tableName = "post_table")
data class Post(val userId: Int, val id: Int, val title: String, val body: String)