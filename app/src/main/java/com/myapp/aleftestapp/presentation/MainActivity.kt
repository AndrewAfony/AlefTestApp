package com.myapp.aleftestapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.myapp.aleftestapp.adapters.ImageAdapter
import com.myapp.aleftestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var imageAdapter: ImageAdapter

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView(emptyList())

        viewModel.images.observe(this){
            setupRecyclerView(it)
        }

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.loadImages()
            binding.swipeToRefreshLayout.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenCreated {
            viewModel.eventFlow.collectLatest {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(data: List<String>) {
        imageAdapter = ImageAdapter(data) { imageUrl ->
            val intent = Intent(this, FullScreenImageActivity::class.java).apply {
                putExtra("image_uri", imageUrl)
            }

            startActivity(intent)
        }
        binding.rvImage.adapter = imageAdapter
    }

}
