package com.twentythirty.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twentythirty.core.domain.usecase.FilmUseCase

/**
 * Created by taufan-mft on 5/24/2021.
 */
class FavoriteViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {
    fun getAllFavoriteFilms() = filmUseCase.getAllFavoriteFilms().asLiveData()
}