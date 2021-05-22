package com.twentythirty.core.utils

import com.twentythirty.core.data.source.local.entity.FilmEntity
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
}
