package com.loperilla.model.ui

import com.loperilla.model.quote.Quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.ui
 * Created By Manuel Lopera on 1/5/23 at 11:41
 * All rights reserved 2023
 */
sealed class AnimeState {
    object Loading : AnimeState()
    object AnimeView : AnimeState()
    data class ResultSearch(val resultList: List<Quote>) : AnimeState()
}
