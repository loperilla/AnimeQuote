package com.loperilla.onboarding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.model.quote.Quote
import com.loperilla.model.result.CallResult
import com.loperilla.model.ui.HomeState
import com.loperilla.model.ui.HomeState.Loading
import com.loperilla.onboarding_domain.usecase.QuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 28/4/23 at 11:52
 * All rights reserved 2023
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val quoteUseCase: QuoteUseCase
) : ViewModel() {

    private var _homeState: MutableStateFlow<HomeState> = MutableStateFlow(Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun getRandomQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            quoteUseCase.getByAnimeTitle("one piece").collect { result: CallResult<List<Quote>> ->
                when (result) {
                    is CallResult.Exception -> {
                        _homeState.value = HomeState.Error(result.errorMsg)
                    }

                    is CallResult.Success -> {
                        _homeState.value = HomeState.Success(result.data ?: emptyList())
                    }
                }
            }
        }
    }
}

