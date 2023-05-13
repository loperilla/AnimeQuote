package com.loperilla.datasource.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.loperilla.datasource.database.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.dao
 * Created By Manuel Lopera on 3/5/23 at 15:49
 * All rights reserved 2023
 */
@Dao
interface QuoteDao {
    @Query("SELECT * FROM QuoteEntity")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Query("SELECT * FROM QuoteEntity WHERE anime LIKE '%' || :animeTitle || '%'")
    fun getQuoteByAnime(animeTitle: String): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM QuoteEntity WHERE character LIKE '%' || :characterName || '%'")
    fun getQuoteByCharacter(characterName: String): Flow<List<QuoteEntity>>

    @Upsert
    fun addAnimeList(characterList: List<QuoteEntity>): LongArray
}
