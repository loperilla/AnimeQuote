package com.loperilla.data.di

import com.loperilla.data.network.QuoteRepository
import com.loperilla.datasource.di.IODispatcher
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
}
