package com.loperilla.datasource.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.di
 * Created By Manuel Lopera on 28/4/23 at 14:06
 * All rights reserved 2023
 */


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {
    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IODispatcher
    fun provideIODispatchers(): CoroutineDispatcher = Dispatchers.IO
}