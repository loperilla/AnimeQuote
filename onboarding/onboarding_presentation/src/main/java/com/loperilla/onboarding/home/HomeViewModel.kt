package com.loperilla.onboarding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.model.database.DatabaseResult
import com.loperilla.model.database.ShoppingItem
import com.loperilla.onboarding.home.model.HomeState
import com.loperilla.onboarding_domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 25/4/23 at 20:48
 * All rights reserved 2023
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private var _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    fun getAllShoppingList() {
        viewModelScope.launch(Dispatchers.IO) {
            homeUseCase.getUserShoppingList().collect { result: DatabaseResult<ShoppingItem> ->
                when(result) {
                    is DatabaseResult.FAIL -> {
                        _homeState.update {
                            it.copy(
                                errorMessage = result.errorMessage
                            )
                        }
                    }
                    is DatabaseResult.GET -> {
                        _homeState.update {
                            it.copy(
                                shoppingListItem = result.getResponse
                            )
                        }
                    }
                    is DatabaseResult.GET_SINGLE -> {

                    }
                }
            }
        }
    }
}