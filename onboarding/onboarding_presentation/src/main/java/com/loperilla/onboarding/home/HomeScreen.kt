package com.loperilla.onboarding.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.loperilla.core_ui.LOW
import com.loperilla.core_ui.MEDIUM
import com.loperilla.core_ui.previews.PIXEL_33_NIGHT
import com.loperilla.model.quote.Quote
import com.loperilla.model.ui.HomeState

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 28/4/23 at 11:50
 * All rights reserved 2023
 */

@Composable
fun HomeScreen(
    homeState: HomeState,
    onLoading: () -> Unit,
    modifier: Modifier
) {
    when (homeState) {
        is HomeState.Error -> {
            TODO()
        }

        HomeState.Loading -> {
            LoadingIndicator(modifier)
            onLoading()
        }

        is HomeState.Success -> {
            QuoteList(modifier, homeState.quoteList)
        }
    }
}

@Composable
private fun LoadingIndicator(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun QuoteList(
    modifier: Modifier,
    quoteList: List<Quote>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            quoteList.size
        ) {
            QuoteItem(quoteList[it])
        }
    }
}

@Composable
fun QuoteItem(quote: Quote) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
            )
            .padding(LOW)
    ) {
        Column {
            Text(
                text = quote.anime,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = MEDIUM, start = LOW, end = LOW)
            )
            Text(
                text = quote.character,
                fontSize = 14.sp,
                modifier = Modifier.padding(LOW)
            )

            Text(
                text = quote.quote,
                fontSize = 14.sp,
                modifier = Modifier.padding(LOW)
            )
        }
    }
}

@PIXEL_33_NIGHT
@Composable
fun QuoteItemPreview() {
    QuoteItem(
        Quote(
            "One Piece",
            "Sanji",
            "I couldn't confess my feelings for you, so I watched you from afar, being happy with someone else."
        )
    )
}