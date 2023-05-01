package com.loperilla.onboarding_domain.usecase.anime

import com.loperilla.data.combined.anime.AnimeRepository
import com.loperilla.model.quote.Anime
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.usecase.anime
 * Created By Manuel Lopera on 30/4/23 at 13:14
 * All rights reserved 2023
 */
class GetAllAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<CallResult<List<Anime>>> =
        repository.getAllAnimeName()
}