package com.loperilla.onboarding_domain.di.login

import com.loperilla.data.firebase.auth.FirebaseAuthRepository
import com.loperilla.data.firebase.database.ShoppingListRepository
import com.loperilla.onboarding_domain.usecase.DoLoginUseCase
import com.loperilla.onboarding_domain.usecase.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding_domain.di.login
 * Created By Manuel Lopera on 23/4/23 at 12:15
 * All rights reserved 2023
 */

@Module
@InstallIn(SingletonComponent::class)
object LoginDependencyInjector {

    @Provides
    fun providesDoLoginUseCase(
        firebaseAuth: FirebaseAuthRepository
    ): DoLoginUseCase = DoLoginUseCase(firebaseAuth)

    @Provides
    fun providesHomeUseCase(
        shoppingListRepository: ShoppingListRepository
    ): HomeUseCase = HomeUseCase(shoppingListRepository)
}
