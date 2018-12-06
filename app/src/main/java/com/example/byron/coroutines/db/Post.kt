package com.example.byron.coroutines.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userId: Int,
    val title: String,
    val body: String
)