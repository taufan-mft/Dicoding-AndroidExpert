package com.twentythirty.core.utils

import com.twentythirty.core.data.source.local.entity.FilmEntity
import com.twentythirty.core.data.source.remote.response.FilmResponse
import com.twentythirty.core.domain.model.Film
import com.twentythirty.core.domain.model.FilmMin

/**
 * Created by taufan-mft on 5/22/2021.
 */
object DataMapper {
    fun mapDomainToEntity(film: Film): FilmEntity =
        FilmEntity(
            title = film.title,
            id = film.id,
            photo = film.posterPath,
            year = film.year
        )

    fun mapEntitiesToUi(filmEntity: List<FilmEntity>): List<FilmMin> {
        val list = mutableListOf<FilmMin>()
        for (film in filmEntity) {
            val payload = FilmMin(
                id = film.id,
                title = film.title,
                year = film.year
            )
            list.add(payload)
        }
        return list
    }

    fun mapResponsesToDomain(film: FilmResponse.Result): Film {
        return Film(
            id = film.id,
            title = film.title,
            releaseDate = film.releaseDate,
            posterPath = film.posterPath,
            backdropPath = film.backdropPath,
            voteAverage = film.voteAverage.toString(),
            year = film.releaseDate.substring(0, 4),
            overview = film.overview,
            rating = film.popularity.toString()
        )

    }
}
