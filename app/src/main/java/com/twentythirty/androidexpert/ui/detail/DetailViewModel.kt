package com.twentythirty.androidexpert.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twentythirty.core.domain.usecase.FilmUseCase

/**
 * Created by taufan-mft on 5/24/2021.
 */
class DetailViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {

    fun getDetailFilm(movieId: Int) = filmUseCase.getFilmDetail(movieId).asLiveData()

}