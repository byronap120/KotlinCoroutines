package com.example.byron.coroutines.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.byron.coroutines.R
import com.example.byron.coroutines.databinding.ActivityMainBinding
import com.example.byron.coroutines.ui.adapters.PostAdapter
import com.example.byron.coroutines.viewModels.PostsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val adapter = PostAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Reference viewModel
        postViewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)

        // Observe viewModel for changes on posts
        postViewModel.allPosts.observe(this, Observer { value ->
            value?.let {
                adapter.setPosts(value)
            }
        })

        // Observe viewModel for changes on spinner
        postViewModel.spinner.observe(this, Observer { value ->
            value?.let { show ->
                binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        binding.floatingButton.setOnClickListener {
            postViewModel.refreshPosts()
        }
    }

}
