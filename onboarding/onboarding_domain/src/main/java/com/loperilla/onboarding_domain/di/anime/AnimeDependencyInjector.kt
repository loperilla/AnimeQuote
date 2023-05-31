package com.loperilla.onboarding_domain.di.anime

import com.loperilla.data.combined.anime.AnimeQuoteRepository
import com.loperilla.data.combined.anime.AnimeRepository
import com.loperilla.onboarding_domain.usecase.anime.GetAllAnimeUseCase
import com.loperilla.onboarding_domain.usecase.combined.AnimeQuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.di.anime
 * Created By Manuel Lopera on 30/4/23 at 13:15
 * All rights reserved 2023
 */

@Module
@InstallIn(ViewModelComponent::class)
object AnimeDependencyInjector {

    @Provides
    fun provideGetAllAnimeUseCase(
        repository: AnimeRepository
    ): GetAllAnimeUseCase = GetAllAnimeUseCase(repository)

    @Provides
    fun providesCombinedAnimeQuoteUseCase(
        repository: AnimeQuoteRepository
    ) = AnimeQuoteUseCase(repository)
}
