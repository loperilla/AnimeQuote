package com.loperilla.datasource.network.api

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.network.Response

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.api
 * Created By Manuel Lopera on 28/4/23 at 11:01
 * All rights reserved 2023
 */
interface QuoteApi {
    suspend fun getRandomQuotes(): Response<List<QuoteNetwork>>
}
