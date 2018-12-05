package com.example.byron.coroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
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
        setContentView(R.layout.activity_main)



        GlobalScope.launch (Dispatchers.Main){
            Log.d(TAG, "start")
            delay(2000)
            Log.d(TAG, "after coroutine")
        }


        //val service = PostService.makeRetrofitService()
        GlobalScope.launch(Dispatchers.Main) {
            val response = service.getPosts().await()
            if (response.isSuccessful) {
                // Pass in the response.body() to your adapter
                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
