package com.twentythirty.androidexpert.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.twentythirty.androidexpert.databinding.ActivityDetailBinding
import com.twentythirty.core.data.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_TAG = "id_tag"
    }

    private lateinit var binding: ActivityDetailBinding
    val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        //supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)

        setContentView(binding.root)
        val id = intent.getIntExtra(ID_TAG, 0)
        //setSupportActionBar(binding.toolbar);
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black_half)));
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        supportActionBar?.elevation = 0F
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        // {
        //     window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //     window.statusBarColor = Color.TRANSPARENT;
        // }

        // window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //window.navigationBarColor = Color.BLACK
        detailViewModel.getDetailFilm(id).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        println(resource.data)
                        val data = resource.data
                        supportActionBar?.title = data?.title
                        Glide.with(this)
                            .load(data?.backdropPath)
                            .into(binding.posterBackDrop)
                        Glide.with(this)
                            .load(data?.posterPath)
                            .into(binding.imgPoster)
                        binding.txTitle.text = data?.title
                        binding.txAge.text = data?.rating
                        binding.txRating.text = data?.voteAverage
                        binding.txTags.text = data?.tags
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