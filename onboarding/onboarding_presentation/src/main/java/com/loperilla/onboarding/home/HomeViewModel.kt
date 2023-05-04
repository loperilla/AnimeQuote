package com.loperilla.onboarding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loperilla.onboarding_domain.usecase.quote.QuotePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 28/4/23 at 11:52
 * All rights reserved 2023
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quoteUseCase: QuotePagingUseCase
) : ViewModel() {

    fun getPagingQuotes() = quoteUseCase
        .getRandomQuotes()
        .cachedIn(viewModelScope)
}

