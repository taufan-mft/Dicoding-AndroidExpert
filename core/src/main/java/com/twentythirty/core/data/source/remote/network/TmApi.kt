package com.twentythirty.core.data.source.remote.network

import com.twentythirty.core.data.source.remote.response.FilmDetailResponse
import com.twentythirty.core.data.source.remote.response.FilmRatingResponse
import com.twentythirty.core.data.source.remote.response.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by taufan-mft on 5/22/2021.
 */
interface TmApi {
    @GET("trending/movie/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getFilms(): List<FilmResponse>

    @GET("https://api.themoviedb.org/3/movie/{movieID}?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmDetail(@Path("movieID") movieID: Int): FilmDetailResponse

    @GET("https://api.themoviedb.org/3/movie/{movieID}/release_dates?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmRating(@Path("movieID") movieID: Int): FilmRatingResponse


}