package com.twentythirty.androidexpert.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.twentythirty.androidexpert.databinding.ActivityFavoriteBinding
import com.twentythirty.androidexpert.ui.home.FilmAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val adapter = FilmAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Films"
        binding.recView.layoutManager = GridLayoutManager(this, 2)
        binding.recView.setHasFixedSize(true)
        binding.recView.adapter = adapter
        setupObserver()
    }

    private fun setupObserver() {
        favoriteViewModel.getAllFavoriteFilms().observe(this, {
            adapter.setData(it)

        })
    }

}