package com.loperilla.datasource.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.loperilla.datasource.database.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.dao
 * Created By Manuel Lopera on 29/4/23 at 15:26
 * All rights reserved 2023
 */
@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity WHERE name LIKE '%' || :filterName || '%'")
    fun getAnimeByName(filterName: String): Flow<List<CharacterEntity>>

    @Upsert
    fun addAnimeList(characterList: List<CharacterEntity>): LongArray
}
