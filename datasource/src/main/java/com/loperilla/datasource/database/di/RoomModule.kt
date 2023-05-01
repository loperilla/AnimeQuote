package com.loperilla.datasource.database.di

import android.content.Context
import androidx.room.Room
import com.loperilla.datasource.database.AnimeDatabase
import com.loperilla.datasource.database.dao.AnimeDao
import com.loperilla.datasource.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.di
 * Created By Manuel Lopera on 29/4/23 at 15:30
 * All rights reserved 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAnimeDatabase(
        context: Context
    ): AnimeDatabase {
        return Room
            .databaseBuilder(
                context,
                AnimeDatabase::class.java,
                AnimeDatabase::class.java.simpleName
            ).build()
    }

    @Provides
    fun provideAnimeDao(
        animeDatabase: AnimeDatabase
    ): AnimeDao = animeDatabase.animeDao()

    @Provides
    fun provideCharacterDao(
        animeDatabase: AnimeDatabase
    ): CharacterDao = animeDatabase.characterDao()

}
