package com.loperilla.data.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.loperilla.data.combined.anime.AnimeQuoteRepository
import com.loperilla.data.combined.anime.AnimeRepository
import com.loperilla.data.combined.character.CharacterRepository
import com.loperilla.data.combined.mediador.QuotePagingSource
import com.loperilla.data.network.QuoteRepository
import com.loperilla.datasource.database.AnimeDatabase
import com.loperilla.datasource.database.dao.AnimeDao
import com.loperilla.datasource.database.dao.CharacterDao
import com.loperilla.datasource.database.dao.QuoteDao
import com.loperilla.datasource.di.IODispatcher
import com.loperilla.datasource.network.api.AnimeApi
import com.loperilla.datasource.network.api.CharactersApi
import com.loperilla.datasource.network.api.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

/*****
 * Project: CompraCasa
 * From: com.loperilla.data.di
 * Created By Manuel Lopera on 23/4/23 at 18:02
 * All rights reserved 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DataDependencyInjector {

    @Provides
    fun providesQuoteRepository(
        quoteApi: QuoteApi,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): QuoteRepository = QuoteRepository(quoteApi, dispatcher)

    @Provides
    fun provideQuoteMediator(
        quoteApi: QuoteApi,
        quoteDao: QuoteDao
    ) = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            QuotePagingSource(quoteApi, quoteDao)
        }
    )

    @Provides
    fun providesAnimeQuoteRepository(
        quoteDb: AnimeDatabase,
        quoteApi: QuoteApi
    ) = AnimeQuoteRepository(quoteDb, quoteApi)

    @Provides
    fun provideAnimeRepository(
        animeApi: AnimeApi,
        animeDao: AnimeDao
    ): AnimeRepository = AnimeRepository(animeApi, animeDao)

    @Provides
    fun provideCharacterRepository(
        characterApi: CharactersApi,
        characterDao: CharacterDao
    ): CharacterRepository = CharacterRepository(characterApi, characterDao)
}
