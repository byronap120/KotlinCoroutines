package com.example.byron.coroutines.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    fun getPosts(): Deferred<Response<List<Post>>>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"

        fun makeRetrofitService(): PostService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(PostService::class.java)
        }
    }
}