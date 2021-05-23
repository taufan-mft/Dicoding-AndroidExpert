package com.twentythirty.core.data.source.local.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twentythirty.core.data.source.local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by taufan-mft on 5/22/2021.
 */
interface FilmDao {
    companion object {
        const val TABLE_NAME = "film_table"
    }

    @Query("SELECT * FROM film_table")
    fun getAllFavoriteFilm(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilm(filmEntity: FilmEntity)

    @Delete
    suspend fun delete(filmEntity: FilmEntity)

    @Query("SELECT COUNT(id) FROM film_table WHERE id =:id")
    suspend fun searchFilm(id: Int): Int
}