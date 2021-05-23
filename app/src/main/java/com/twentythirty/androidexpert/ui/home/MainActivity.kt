package com.twentythirty.androidexpert.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.twentythirty.androidexpert.databinding.ActivityMainBinding
import com.twentythirty.core.data.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter = FilmAdapter()
    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0F
        supportActionBar?.title = "Movie"

        binding.recView.layoutManager = GridLayoutManager(this, 2)
        binding.recView.setHasFixedSize(true)
        binding.recView.adapter = adapter

        setObservers()
    }

    private fun setObservers() {
        homeViewModel.films.observe(this, { paket ->
            when (paket.status) {
                Status.SUCCESS -> {
                    paket.data?.let {
                        adapter.setData(it)
                    }
                }
            }
        })
    }
}