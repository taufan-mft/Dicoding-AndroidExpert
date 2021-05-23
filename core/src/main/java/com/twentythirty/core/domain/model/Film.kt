package com.twentythirty.core.domain.model

/**
 * Created by taufan-mft on 5/22/2021.
 */
data class Film(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: String,
    val year: String,
    val overview: String,
    val rating: String?
)