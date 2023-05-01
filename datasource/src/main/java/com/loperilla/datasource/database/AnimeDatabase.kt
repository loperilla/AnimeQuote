package com.loperilla.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.loperilla.datasource.database.dao.AnimeDao
import com.loperilla.datasource.database.dao.CharacterDao
import com.loperilla.datasource.database.entity.AnimeEntity
import com.loperilla.datasource.database.entity.CharacterEntity

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database
 * Created By Manuel Lopera on 29/4/23 at 14:21
 * All rights reserved 2023
 */
@Database(entities = [AnimeEntity::class, CharacterEntity::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun characterDao(): CharacterDao
}