package com.loperilla.onboarding.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.loperilla.core_ui.LOW
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
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = quoteList
        ) { quote ->
            if (quote != null) {
                QuoteItem(quote)
            }
        }
        when (val state = quoteList.loadState.refresh) {
            is LoadState.Error -> {

            }

            LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(LOW),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }

            }

            is LoadState.NotLoading -> {

            }
        }
        when (val state = quoteList.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}


