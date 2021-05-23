package com.twentythirty.core.data.source.local

import com.twentythirty.core.data.source.local.entity.FilmEntity
import com.twentythirty.core.data.source.local.room.FilmDao
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/23/2021.
 */
class LocalDataSource(private val filmDao: FilmDao) {

    fun getAllFavoriteFilms(): Flow<List<FilmEntity>> = filmDao.getAllFavoriteFilm()

    suspend fun insertFilm(filmEntity: FilmEntity) = filmDao.insertFilm(filmEntity)

    suspend fun setFavoriteFilm(filmEntity: FilmEntity, newState: Boolean) {
        if (newState) {
            filmDao.insertFilm(filmEntity)
        } else {
            filmDao.delete(filmEntity)
        }
    }

    suspend fun isFilmLiked(id: Int): Boolean = filmDao.searchFilm(id) > 0
}