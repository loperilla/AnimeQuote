package com.loperilla.data.network

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.model.quote.Quote
import com.loperilla.model.result.CallResult
import com.loperilla.model.result.ErrorType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.data.network
 * Created By Manuel Lopera on 28/4/23 at 11:10
 * All rights reserved 2023
 */
class QuoteRepository @Inject constructor(
    private val quoteApi: QuoteApi,
    private val dispatcher: CoroutineDispatcher
) {

    // QuotePagingSource
//    suspend fun getRandomQuotes(): Flow<CallResult<List<Quote>>> = flow {
//        val result: Result<List<QuoteNetwork>> = quoteApi.getRandomQuotes()
//        if (result.isFailure || result.getOrNull().isNullOrEmpty()) {
//            emit(CallResult.Exception("Nulo o vacío"))
//        } else {
//            val networkQuoteList: List<QuoteNetwork> = result.getOrThrow()
//            emit(CallResult.Success(
//                networkQuoteList.map {
//                    it.toDomain()
//                }
//            ))
//        }
//    }.flowOn(dispatcher)

    //QuoteAnimePagingSource
    suspend fun getQuotesByAnimeTitle(title: String): Flow<CallResult<List<Quote>>> = flow {
        val result: Result<List<QuoteNetwork>> = quoteApi.getRandomQuotesByAnimeTitle(title, 1)
        if (result.isFailure || result.getOrNull().isNullOrEmpty()) {
            emit(CallResult.Exception(ErrorType.UncontrolledError(22)))
        } else {
            val networkQuoteList: List<QuoteNetwork> = result.getOrThrow()
            emit(
                CallResult.Success(
                    networkQuoteList.map {
                        it.toDomain()
                    }
                ))
        }
    }.flowOn(dispatcher)

    suspend fun getQuotesByCharacterName(name: String): Flow<CallResult<List<Quote>>> = flow {
        val result: Result<List<QuoteNetwork>> = quoteApi.getRandomQuotesByCharacter(name)
        if (result.isFailure || result.getOrNull().isNullOrEmpty()) {
            emit(CallResult.Exception(ErrorType.UncontrolledError(2367823)))
        } else {
            val networkQuoteList: List<QuoteNetwork> = result.getOrThrow()
            emit(CallResult.Success(
                networkQuoteList.map {
                    it.toDomain()
                }
            ))
        }
    }.flowOn(dispatcher)
}
