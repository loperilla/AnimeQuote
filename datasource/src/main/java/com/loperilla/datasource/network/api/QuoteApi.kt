package com.loperilla.datasource.network.api

import com.loperilla.datasource.model.QuoteNetwork

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.api
 * Created By Manuel Lopera on 28/4/23 at 11:01
 * All rights reserved 2023
 */
interface QuoteApi {
    suspend fun getRandomQuotes(): Result<List<QuoteNetwork>>
    suspend fun getRandomQuotesByAnimeTitle(animeTitle: String): Result<List<QuoteNetwork>>
    suspend fun getRandomQuotesByCharacter(name: String): Result<List<QuoteNetwork>>
}
