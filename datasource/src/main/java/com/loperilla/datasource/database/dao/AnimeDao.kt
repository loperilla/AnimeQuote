package com.loperilla.datasource.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.loperilla.datasource.database.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.dao
 * Created By Manuel Lopera on 29/4/23 at 15:18
 * All rights reserved 2023
 */
@Dao
interface AnimeDao {

    @Query("SELECT * FROM AnimeEntity")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM AnimeEntity WHERE name LIKE '%' || :filterName || '%'")
    fun getAnimeByName(filterName: String): Flow<List<AnimeEntity>>

    @Upsert
    fun addAnimeList(animeList: List<AnimeEntity>): LongArray
}