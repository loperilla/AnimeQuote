package com.loperilla.datasource.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.loperilla.datasource.database.entity.QuoteEntity
import com.loperilla.model.quote.Quote

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
    suspend fun getQuoteByAnime(animeTitle: String): List<QuoteEntity>

    @Query("SELECT * FROM QuoteEntity WHERE character LIKE '%' || :characterName || '%' LIMIT :pageSize OFFSET :startPage")
    fun getQuoteByAnimePaged(
        characterName: String,
        startPage: Int,
        pageSize: Int = 10
    ): PagingSource<Int, Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuoteList(characterList: List<QuoteEntity>): LongArray
}
