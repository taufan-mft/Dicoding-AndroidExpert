package com.twentythirty.core.data

import com.twentythirty.core.data.source.local.LocalDataSource
import com.twentythirty.core.data.source.remote.RemoteDataSource
import com.twentythirty.core.domain.model.Film
import com.twentythirty.core.domain.repository.IFilmRepository
import com.twentythirty.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by taufan-mft on 5/23/2021.
 */
class FilmRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IFilmRepository {
    override fun getFilms(): Flow<Resource<List<Film>>> {
        return flow {
            val response = remoteDataSource.getFilms()
            if (response.status == Status.SUCCESS) {
                emit(Resource.success(response.data!!.results.map {
                    DataMapper.mapResponsesToDomain(
                        it
                    )
                }))
            } else {
                emit(
                    Resource.error(
                        data = null,
                        message = response.message ?: "Error Occurred!"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<Film>> {
        return flow {
            val response = remoteDataSource.getFilmDetail(movieId)
            val ratingResponse = remoteDataSource.getFilmRating(movieId)
            emit(Resource.success(DataMapper.mapResponsesToDomain(response, ratingResponse)))
        }
    }

    override fun getFavoriteFilms(): Flow<List<Film>> {
        return localDataSource.getAllFavoriteFilms().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(film: Film, state: Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            localDataSource.setFavoriteFilm(DataMapper.mapDomainToEntity(film), state)
        }
    }

    override suspend fun getFavoriteState(film: Film): Boolean {
        return localDataSource.isFilmLiked(film.id)
    }


}