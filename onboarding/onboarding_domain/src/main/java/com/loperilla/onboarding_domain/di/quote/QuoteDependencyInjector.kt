package com.loperilla.onboarding_domain.di.quote

import com.loperilla.data.network.QuoteRepository
import com.loperilla.onboarding_domain.usecase.QuoteUseCase
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
object QuoteDependencyInjector {

    @Provides
    fun providesDoLoginUseCase(
        quoteRepository: QuoteRepository
    ): QuoteUseCase = QuoteUseCase(quoteRepository)

//
//    @Provides
//    fun providesHomeUseCase(
//        shoppingListRepository: ShoppingListRepository
//    ): HomeUseCase = HomeUseCase(shoppingListRepository)
}
