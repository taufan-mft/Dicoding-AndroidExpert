package com.twentythirty.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class FilmRatingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("release_dates")
        val releaseDates: List<ReleaseDate>
    ) {
        data class ReleaseDate(
            @SerializedName("certification")
            val certification: String,
            @SerializedName("iso_639_1")
            val iso6391: String,
            @SerializedName("note")
            val note: String,
            @SerializedName("release_date")
            val releaseDate: String,
            @SerializedName("type")
            val type: Int
        )
    }
}