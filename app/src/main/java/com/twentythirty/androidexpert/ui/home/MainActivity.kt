package com.twentythirty.androidexpert.ui.home

import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.twentythirty.androidexpert.R
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        menu.apply {
            for (index in 0 until this.size()) {
                val item = this.getItem(index)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    item.icon.colorFilter = BlendModeColorFilter(
                        Color.BLACK, BlendMode.SRC_IN
                    )
                } else {
                    item.icon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fav -> {
                val uri = Uri.parse("topanapp://favs")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
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