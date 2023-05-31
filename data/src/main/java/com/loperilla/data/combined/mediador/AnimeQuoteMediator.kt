package com.loperilla.data.combined.mediador

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.loperilla.datasource.database.AnimeDatabase
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.model.quote.Quote
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AnimeQuoteMediator(
    private val animeName: String,
    private val startPage: Int,
    private val database: AnimeDatabase,
    private val quoteApi: QuoteApi
) : RemoteMediator<Int, Quote>() {
    val TAG = AnimeQuoteMediator::class.java.simpleName
    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, Quote>
    ): MediatorResult {
        return try {
            val endPaging = MediatorResult.Success(endOfPaginationReached = true)
            val continuePaging = MediatorResult.Success(endOfPaginationReached = false)
            val loadKey = when (loadType) {
                LoadType.REFRESH -> startPage
                LoadType.PREPEND -> return endPaging
                LoadType.APPEND -> {
                    startPage - 1
                }
            }
            if (loadKey <= 0) {
                return endPaging
            }
            Log.i(TAG, "Page No, Load Key: $loadKey")

            val response = quoteApi.getRandomQuotesByAnimeTitle(animeName, startPage)

            Log.i(TAG, "Fetch ${response.getOrNull()?.size} episodes, ${loadType.name}")
            return if (response.getOrNull().isNullOrEmpty()) {
                endPaging
            } else {
                response.getOrNull()?.let {
                    it.map { net ->
                        net.toEntity()
                    }
                }?.let {
                    database.quoteDao().addQuoteList(
                        it
                    )
                }
                continuePaging
            }
        } catch (e: IOException) {
            Log.e(TAG, "FAILED Io Error", e)
            MediatorResult.Error(e)
        }
    }
}
