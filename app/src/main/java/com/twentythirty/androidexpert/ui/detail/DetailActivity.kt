package com.twentythirty.androidexpert.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.twentythirty.androidexpert.databinding.ActivityDetailBinding
import com.twentythirty.core.data.Status
import com.twentythirty.core.domain.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_TAG = "id_tag"
    }

    private lateinit var binding: ActivityDetailBinding
    val detailViewModel: DetailViewModel by viewModel()
    private var isLiked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        setObservers()

    }

    private fun setClickHandler(film: Film) {
        binding.floatingActionButton.setOnClickListener {
            if (!isLiked) {
                detailViewModel.setFavoriteFilm(film, true)
                binding.floatingActionButton.setColorFilter(Color.WHITE)
            } else {
                detailViewModel.setFavoriteFilm(film, false)
                binding.floatingActionButton.setColorFilter(Color.BLACK)
            }
        }
    }

    private fun checkFavorite(film: Film) {
        lifecycleScope.launch(Dispatchers.Main) {
            isLiked = detailViewModel.getFavoriteState(film)
            if (isLiked) {
                binding.floatingActionButton.setColorFilter(Color.WHITE)
            }
        }
    }

    private fun setObservers() {
        val id = intent.getIntExtra(ID_TAG, 0)
        detailViewModel.getDetailFilm(id).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        println(resource.data)
                        val data = resource.data
                        checkFavorite(data!!)
                        setClickHandler(data)
                        supportActionBar?.title = data.title
                        Glide.with(this)
                            .load(data.backdropPath)
                            .into(binding.posterBackDrop)
                        Glide.with(this)
                            .load(data.posterPath)
                            .into(binding.imgPoster)
                        binding.txTitle.text = data.title
                        binding.txAge.text = data.rating
                        binding.txRating.text = data.voteAverage
                        binding.txTags.text = data.tags
                        binding.txYear.text = data.year
                        binding.txOverview.text = data.overview
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, resource.message, Toast.LENGTH_SHORT)
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}