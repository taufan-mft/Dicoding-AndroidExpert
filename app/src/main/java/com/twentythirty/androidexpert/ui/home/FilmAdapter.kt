package com.twentythirty.androidexpert.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twentythirty.androidexpert.databinding.ItemRowFilmBinding
import com.twentythirty.androidexpert.ui.detail.DetailActivity
import com.twentythirty.core.domain.model.Film

/**
 * Created by taufan-mft on 5/22/2021.
 */
class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private val listFilms = ArrayList<Film>()

    class FilmViewHolder(private val binding: ItemRowFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            with(binding) {
                tvYear.text = film.releaseDate.subSequence(0, 4)
                Glide
                    .with(imgView.context)
                    .load("https://image.tmdb.org/t/p/original/${film.posterPath}")
                    .into(imgView)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ID_TAG, film.id)
                itemView.context.startActivity(intent)
            }
        }

    }

    fun setData(listFilms: List<Film>) {
        this.listFilms.clear()
        this.listFilms.addAll(listFilms)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemRowFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }

}