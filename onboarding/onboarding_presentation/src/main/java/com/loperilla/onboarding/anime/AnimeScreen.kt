package com.loperilla.onboarding.anime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loperilla.model.quote.Anime
import com.loperilla.model.ui.AnimeState
import com.loperilla.onboarding.common.QuoteList

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.anime
 * Created By Manuel Lopera on 1/5/23 at 12:08
 * All rights reserved 2023
 */

@Composable
fun AnimeScreen(
    modifier: Modifier,
    animeState: AnimeState,
    animeList: List<Anime>,
    isSearching: Boolean,
    onLoadingState: () -> Unit,
    onSelectAnime: (Anime) -> Unit
) {
    when (animeState) {
        AnimeState.AnimeView -> {
            if (isSearching) {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                SearchAnime(
                    modifier = modifier,
                    animeList = animeList,
                    onSelectedAnime = {
                        onSelectAnime(it)
                    }
                )
            }
        }

        AnimeState.Loading -> {
            onLoadingState()
        }

        is AnimeState.ResultSearch -> {
            QuoteList(
                modifier,
                animeState.resultList
            )
        }
    }
}