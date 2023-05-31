package com.loperilla.onboarding_domain.usecase.combined

import com.loperilla.data.combined.anime.AnimeQuoteRepository
import javax.inject.Inject

class AnimeQuoteUseCase @Inject constructor(
    private val repository: AnimeQuoteRepository
) {
    fun getQuotesByAnime(
        animeName: String,
        page: Int
    ) = repository.getQuotesByAnimeName(animeName, page)

}