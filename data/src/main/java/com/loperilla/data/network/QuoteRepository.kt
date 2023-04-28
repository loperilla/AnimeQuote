package com.loperilla.data.network

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.model.toDomain
import com.loperilla.datasource.network.Response
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.model.quote.Quote
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.data.network
 * Created By Manuel Lopera on 28/4/23 at 11:10
 * All rights reserved 2023
 */
class QuoteRepository @Inject constructor(
    private val quoteApi: QuoteApi
) {
    suspend fun getRandomQuotes(): Flow<CallResult<List<Quote>>> = flow {
        when (val response = quoteApi.getRandomQuotes()) {
            is Response.Exception -> {
                emit(CallResult.Exception(response.errorMsg))
            }

            is Response.Success -> {
                val networkQuoteList: List<QuoteNetwork>? = response.data
                if (networkQuoteList == null) {
                    emit(CallResult.Success(emptyList()))
                    return@flow
                }
                emit(
                    CallResult.Success(networkQuoteList.map {
                        it.toDomain()
                    })
                )
            }
        }
    }
}
