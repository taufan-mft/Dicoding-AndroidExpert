package com.twentythirty.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.twentythirty.core.data.source.local.room.FilmDao

/**
 * Created by taufan-mft on 5/22/2021.
 */
@Entity(tableName = FilmDao.TABLE_NAME)
data class FilmEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val photo: String,
    val year: String,
)