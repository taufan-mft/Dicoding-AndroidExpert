package com.twentythirty.androidexpert.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.twentythirty.core.domain.usecase.FilmUseCase

/**
 * Created by taufan-mft on 5/23/2021.
 */
class HomeViewModel(filmUseCase: FilmUseCase) : ViewModel() {
    val films = filmUseCase.getAllFilms().asLiveData()
}