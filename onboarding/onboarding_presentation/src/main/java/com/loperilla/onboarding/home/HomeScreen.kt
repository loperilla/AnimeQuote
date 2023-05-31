package com.loperilla.onboarding.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.loperilla.core_ui.MEDIUM
import com.loperilla.model.quote.Quote
import com.loperilla.onboarding.common.QuoteItem

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 28/4/23 at 11:50
 * All rights reserved 2023
 */

@Composable
fun HomeScreen(
    pagingItems: LazyPagingItems<Quote>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (pagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            QuotePagingList(modifier, pagingItems)
        }
    }
}

@Composable
fun QuotePagingList(
    modifier: Modifier = Modifier,
    quoteList: LazyPagingItems<Quote>
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (quoteList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MEDIUM),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = quoteList.itemCount,
                    key = quoteList.itemKey(),
                    contentType = quoteList.itemContentType()
                ) { index ->
                    val item = quoteList[index]
                    if (item != null) {
                        QuoteItem(item)
                    }
                }
                item {
                    if (quoteList.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}


