package com.loperilla.onboarding_domain.di.quote

import androidx.paging.Pager
import com.loperilla.data.network.QuoteRepository
import com.loperilla.model.quote.Quote
import com.loperilla.onboarding_domain.usecase.quote.QuotePagingUseCase
import com.loperilla.onboarding_domain.usecase.quote.QuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding_domain.di.login
 * Created By Manuel Lopera on 23/4/23 at 12:15
 * All rights reserved 2023
 */

@Module
@InstallIn(ViewModelComponent::class)
object QuoteDependencyInjector {
    @Provides
    fun providesQuoteUseCase(
        quoteRepository: QuoteRepository
    ): QuoteUseCase = QuoteUseCase(quoteRepository)

    @Provides
    fun provideQuotePagingUseCase(
        pager: Pager<Int, Quote>
    ) = QuotePagingUseCase(pager)
}
