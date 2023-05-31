package com.loperilla.onboarding.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loperilla.model.quote.Anime
import com.loperilla.model.result.CallResult
import com.loperilla.model.ui.AnimeState
import com.loperilla.onboarding_domain.usecase.anime.GetAllAnimeUseCase
import com.loperilla.onboarding_domain.usecase.combined.AnimeQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.anime
 * Created By Manuel Lopera on 1/5/23 at 10:43
 * All rights reserved 2023
 */
@HiltViewModel
@OptIn(FlowPreview::class)
class AnimeViewModel @Inject constructor(
    private val getAllAnimeUseCase: GetAllAnimeUseCase,
    private val animeQuoteUseCase: AnimeQuoteUseCase
) : ViewModel() {

    private var _animeState: MutableStateFlow<AnimeState> = MutableStateFlow(AnimeState.Loading)
    val animeState: StateFlow<AnimeState> = _animeState

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    private var page = 0

    private val _animeList = MutableStateFlow(listOf<Anime>())
    val animeList = searchText
        .debounce(1.seconds)
        .onEach { _isSearching.update { true } }
        .combine(_animeList) { text, animeList ->
            if (text.isBlank()) {
                animeList
            } else {
                delay(2.seconds)
                animeList.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _animeList.value
        )

    private val _animePagingQuotes = MutableStateFlow<Anime?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val quotes = _animePagingQuotes
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { anime ->
            animeQuoteUseCase
                .getQuotesByAnime(anime.name, page).flow
        }.onEach {
            _animeState.update {
                AnimeState.ResultSearch
            }
            page += 1
        }.cachedIn(viewModelScope)

    fun getAllAnime() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllAnimeUseCase().collect { result: CallResult<List<Anime>> ->
                when (result) {
                    is CallResult.Exception -> {
                        //
                    }

                    is CallResult.Success -> {
                        _animeList.value = result.data!!
                        _animeState.update {
                            AnimeState.AnimeView
                        }
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun selectAnime(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchText.value = anime.name
            _animePagingQuotes.value = anime
        }
    }

    fun onInputFocused(isInputFocused: Boolean) {
        if (isInputFocused) {
            viewModelScope.launch(Dispatchers.IO) {
                _animeState.update {
                    AnimeState.AnimeView
                }
            }
        }
    }
}
