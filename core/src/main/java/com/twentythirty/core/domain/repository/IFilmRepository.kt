package com.twentythirty.core.domain.repository

import com.twentythirty.core.data.Resource
import com.twentythirty.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/22/2021.
 */
interface IFilmRepository {
    fun getFilms(): Flow<Resource<List<Film>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<Film>>
    fun getFavoriteFilms(): Flow<List<Film>>
    fun setFavoriteMovie(film: Film, state: Boolean)

    suspend fun getFavoriteState(film: Film): Boolean
}