package com.loperilla.onboarding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loperilla.model.ui.HomeState
import com.loperilla.model.ui.HomeState.Loading
import com.loperilla.onboarding_domain.usecase.quote.QuotePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var _homeState: MutableStateFlow<HomeState> = MutableStateFlow(Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun getPagingQuotes() = quoteUseCase
        .getRandomQuotes()
        .cachedIn(viewModelScope)

//    fun getRandomQuotes() {
//        viewModelScope.launch(Dispatchers.IO) {
//            quoteUseCase.getRandomQuotes().collect { result: CallResult<List<Quote>> ->
//                when (result) {
//                    is CallResult.Exception -> {
//                        _homeState.value = HomeState.Error(result.errorMsg)
//                    }
//
//                    is CallResult.Success -> {
//                        _homeState.value = HomeState.Success(result.data ?: emptyList())
//                    }
//                }
//            }
//        }
//    }
}

