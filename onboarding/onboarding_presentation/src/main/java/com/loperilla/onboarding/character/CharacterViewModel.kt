package com.loperilla.onboarding.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loperilla.model.quote.Character
import com.loperilla.model.quote.Quote
import com.loperilla.model.result.CallResult
import com.loperilla.model.ui.CharacterState
import com.loperilla.onboarding_domain.usecase.QuoteUseCase
import com.loperilla.onboarding_domain.usecase.character.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.character
 * Created By Manuel Lopera on 1/5/23 at 18:51
 * All rights reserved 2023
 */
@HiltViewModel
@OptIn(FlowPreview::class)
class CharacterViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val quoteUseCase: QuoteUseCase
) : ViewModel() {
    private var _characterState: MutableStateFlow<CharacterState> = MutableStateFlow(CharacterState.Loading)
    val characterState: StateFlow<CharacterState> = _characterState

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    private val _characterList = MutableStateFlow(listOf<Character>())
    val characterList = searchText
        .debounce(1.seconds)
        .onEach { _isSearching.update { true } }
        .combine(_characterList) { text, characterList ->
            if (text.isBlank()) {
                characterList
            } else {
                delay(2.seconds)
                characterList.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _characterList.value
        )

    fun getAllCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllCharactersUseCase().collect { result: CallResult<List<Character>> ->
                when (result) {
                    is CallResult.Exception -> {
                        //
                    }

                    is CallResult.Success -> {
                        _characterList.value = result.data!!
                        _characterState.update {
                            CharacterState.CharacterView
                        }
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun selectCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchText.value = character.name
            quoteUseCase.getByCharacterName(character.name).collect { result: CallResult<List<Quote>> ->
                when (result) {
                    is CallResult.Exception -> TODO()
                    is CallResult.Success -> {
                        _characterState.update {
                            CharacterState.ResultSearch(result.data ?: emptyList())
                        }
                    }
                }
            }
        }
    }

    fun onInputFocused(isInputFocused: Boolean) {
        if (isInputFocused) {
            viewModelScope.launch(Dispatchers.IO) {
                _characterState.update {
                    CharacterState.CharacterView
                }
            }
        }
    }
}
