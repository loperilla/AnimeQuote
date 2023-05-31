package com.loperilla.data.combined.mediador

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loperilla.datasource.database.dao.QuoteDao
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.model.quote.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.data.combined.mediador
 * Created By Manuel Lopera on 3/5/23 at 15:15
 * All rights reserved 2023
 */
class QuotePagingSource @Inject constructor(
    private val api: QuoteApi,
    private val quoteDao: QuoteDao
) : PagingSource<Int, Quote>() {
    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return withContext(Dispatchers.IO) {
            val currentPage = params.key ?: 0
            try {
                val result = api.getRandomQuotes(currentPage)
                if (result.isFailure) {
                    val cachedList = quoteDao.getAllQuotes()
                    if (cachedList.isEmpty()) {
                        LoadResult.Error(result.exceptionOrNull() ?: IOException("yoqse"))
                    } else {
                        LoadResult.Page(
                            data = cachedList.map {
                                it.toDomain()
                            },
                            prevKey = if (currentPage == 0) null else currentPage.minus(1),
                            nextKey = if (cachedList.isEmpty()) null else currentPage.plus(1)
                        )
                    }
                } else {
                    val networkQuoteList = result.getOrElse {
                        emptyList()
                    }
                    quoteDao.addQuoteList(
                        networkQuoteList.map {
                            it.toEntity()
                        }
                    )

                    LoadResult.Page(
                        data = quoteDao.getAllQuotes().map {
                            it.toDomain()
                        },
                        prevKey = if (currentPage == 0) null else currentPage.minus(1),
                        nextKey = if (networkQuoteList.isEmpty()) null else currentPage.plus(1)
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
                LoadResult.Error(e)
            }
        }
    }
}
