package com.twentythirty.core.data.source.remote

import com.twentythirty.core.data.Resource
import com.twentythirty.core.data.source.remote.network.TmApi
import com.twentythirty.core.data.source.remote.response.FilmDetailResponse
import com.twentythirty.core.data.source.remote.response.FilmRatingResponse
import com.twentythirty.core.data.source.remote.response.FilmResponse

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


    suspend fun getFilmDetail(movieId: Int): FilmDetailResponse {
        return TmApi.getFilmDetail(movieId)
    }


    suspend fun getFilmRating(movieId: Int): FilmRatingResponse {
        return TmApi.getFilmRating(movieId)
    }
}