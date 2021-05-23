package com.twentythirty.core.data.source.remote

import com.twentythirty.core.data.Resource
import com.twentythirty.core.data.source.remote.network.TmApi
import com.twentythirty.core.data.source.remote.response.FilmDetailResponse
import com.twentythirty.core.data.source.remote.response.FilmRatingResponse
import com.twentythirty.core.data.source.remote.response.FilmResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by taufan-mft on 5/23/2021.
 */
class RemoteDataSource(private val TmApi: TmApi) {
    suspend fun getFilms(): Resource<FilmResponse> {
        return try {
            Resource.success(TmApi.getFilms())
        } catch (e: Exception) {
            Resource.error(
                data = null,
                message = e.message ?: "Error Occurred!"
            )

        }
    }


    suspend fun getFilmDetail(movieId: Int): Flow<FilmDetailResponse> {
        return flow {
            emit(TmApi.getFilmDetail(movieId))
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getFilmRating(movieId: Int): Flow<FilmRatingResponse> {
        return flow {
            emit(TmApi.getFilmRating(movieId))
        }
    }
}