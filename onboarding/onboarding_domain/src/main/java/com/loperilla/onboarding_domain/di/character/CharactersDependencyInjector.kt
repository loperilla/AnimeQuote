package com.loperilla.onboarding_domain.di.character

import com.loperilla.data.combined.character.CharacterRepository
import com.loperilla.onboarding_domain.usecase.character.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.di.character
 * Created By Manuel Lopera on 1/5/23 at 18:48
 * All rights reserved 2023
 */

@Module
@InstallIn(ViewModelComponent::class)
object CharactersDependencyInjector {

    @Provides
    fun provideGetAllCharactersUseCase(
        repository: CharacterRepository
    ): GetAllCharactersUseCase = GetAllCharactersUseCase(repository)
}
