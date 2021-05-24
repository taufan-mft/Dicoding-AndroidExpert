package com.twentythirty.core.domain.usecase

import com.twentythirty.core.data.FilmRepository
import com.twentythirty.core.data.Resource
import com.twentythirty.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/23/2021.
 */
class FilmInteractor(private val filmRepository: FilmRepository) : FilmUseCase {
    override fun getAllFilms(): Flow<Resource<List<Film>>> = filmRepository.getFilms()
    override fun getFilmDetail(movieId: Int): Flow<Resource<Film>> =
        filmRepository.getMovieDetail(movieId)

    override fun getAllFavoriteFilms(): Flow<List<Film>> = filmRepository.getFavoriteFilms()
    override fun setFavoriteFilm(film: Film, state: Boolean) =
        filmRepository.setFavoriteMovie(film, state)

    override suspend fun getFavoriteState(film: Film): Boolean {
        return filmRepository.getFavoriteState(film)
    }
}