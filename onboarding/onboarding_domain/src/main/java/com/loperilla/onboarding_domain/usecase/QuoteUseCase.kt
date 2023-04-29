package com.loperilla.onboarding_domain.usecase

import com.loperilla.data.network.QuoteRepository
import com.loperilla.model.quote.Quote
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.usecase
 * Created By Manuel Lopera on 28/4/23 at 11:45
 * All rights reserved 2023
 */
class QuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend fun getRandomQuotes(): Flow<CallResult<List<Quote>>> = quoteRepository.getRandomQuotes()
    suspend fun getByAnimeTitle(title: String): Flow<CallResult<List<Quote>>> =
        quoteRepository.getQuotesByAnimeTitle(title)
}
