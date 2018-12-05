package com.example.byron.coroutines.ui.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.byron.coroutines.Post
import com.example.byron.coroutines.PostService
import com.example.byron.coroutines.R
import com.example.byron.coroutines.databinding.ActivityMainBinding
import com.example.byron.coroutines.ui.adapters.PostAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG: String = "com.byron.test"

    private val service by lazy {
        PostService.makeRetrofitService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val adapter = PostAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)



        val tempList: List<Post> = listOf(
            Post(1, 1, "asdf", "asdf"),
            Post(1, 1, "asdf", "asdf")
        )
        adapter.setPosts(tempList)

//        GlobalScope.launch(Dispatchers.Main) {
//            Log.d(TAG, "start")
//            delay(2000)
//            Log.d(TAG, "after coroutine")
//        }
//
//
//        //val service = PostService.makeRetrofitService()
//        GlobalScope.launch(Dispatchers.Main) {
//            val response = service.getPosts().await()
//            if (response.isSuccessful) {
//                // Pass in the response.body() to your adapter
//                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}
