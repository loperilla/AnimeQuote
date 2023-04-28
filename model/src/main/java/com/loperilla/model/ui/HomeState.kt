package com.loperilla.model.ui

import com.loperilla.model.quote.Quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.ui
 * Created By Manuel Lopera on 28/4/23 at 11:58
 * All rights reserved 2023
 */
sealed class HomeState {
    object Loading : HomeState()
    data class Success(val quoteList: List<Quote>) : HomeState()
    data class Error(val msg: String) : HomeState()
}
