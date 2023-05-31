package com.loperilla.data.combined.anime

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.loperilla.data.combined.mediador.AnimeQuoteMediator
import com.loperilla.datasource.database.AnimeDatabase
import com.loperilla.datasource.network.api.QuoteApi
import javax.inject.Inject

class AnimeQuoteRepository @Inject constructor(
    private val quoteDb: AnimeDatabase,
    private val quoteApi: QuoteApi
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getQuotesByAnimeName(animeName: String, startPage: Int) = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true
        ),
        remoteMediator = AnimeQuoteMediator(animeName, startPage, quoteDb, quoteApi),
    ) {
        quoteDb.quoteDao().getQuoteByAnimePaged(animeName, startPage)
    }

}

