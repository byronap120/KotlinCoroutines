package com.example.byron.coroutines.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Post::class], version = 1)
abstract class PostRoomDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private lateinit var INSTANCE: PostRoomDatabase

        /**
         * Instantiate a database from a context.
         */
        fun getDatabase(context: Context): PostRoomDatabase {
            synchronized(PostRoomDatabase::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            PostRoomDatabase::class.java,
                            "posts_db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

    }

}