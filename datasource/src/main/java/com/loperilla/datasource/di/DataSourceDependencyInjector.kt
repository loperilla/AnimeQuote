package com.loperilla.datasource.di

import android.content.Context
import com.loperilla.datasource.datastore.IUserDataStoreDataSource
import com.loperilla.datasource.datastore.UserDataStoreDataSourceImpl
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.datasource.network.impl.QuoteImpl
import com.loperilla.datasource.network.ktorHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

/*****
 * Project: CompraCasa
 * From: com.loperilla.datasource.di
 * Created By Manuel Lopera on 23/4/23 at 14:03
 * All rights reserved 2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceDependencyInjector {

    @Singleton
    @Provides
    fun providesPreferenceDataStore(
        context: Context
    ): IUserDataStoreDataSource = UserDataStoreDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = ktorHttpClient

    @Singleton
    @Provides
    fun provideQuoteApi(
        httpClient: HttpClient
    ): QuoteApi = QuoteImpl(httpClient)
}
