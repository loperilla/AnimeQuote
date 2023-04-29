package com.loperilla.onboarding.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loperilla.model.ui.HomeState
import com.loperilla.onboarding.common.QuoteList

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
    modifier: Modifier = Modifier
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
