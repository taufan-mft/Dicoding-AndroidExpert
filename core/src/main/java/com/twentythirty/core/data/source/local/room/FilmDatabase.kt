package com.twentythirty.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twentythirty.core.data.source.local.entity.FilmEntity

/**
 * Created by taufan-mft on 5/23/2021.
 */
@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}