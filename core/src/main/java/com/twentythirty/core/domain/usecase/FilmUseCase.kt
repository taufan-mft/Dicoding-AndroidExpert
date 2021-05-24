package com.twentythirty.core.domain.usecase

import com.twentythirty.core.data.Resource
import com.twentythirty.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/22/2021.
 */
interface FilmUseCase {
    fun getAllFilms(): Flow<Resource<List<Film>>>

    fun getFilmDetail(movieId: Int): Flow<Resource<Film>>
}