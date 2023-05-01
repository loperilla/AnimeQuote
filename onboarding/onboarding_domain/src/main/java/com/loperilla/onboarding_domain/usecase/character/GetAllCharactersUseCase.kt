package com.loperilla.onboarding_domain.usecase.character

import com.loperilla.data.combined.character.CharacterRepository
import com.loperilla.model.quote.Character
import com.loperilla.model.result.CallResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding_domain.usecase.character
 * Created By Manuel Lopera on 1/5/23 at 18:46
 * All rights reserved 2023
 */
class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<CallResult<List<Character>>> =
        repository.getAllCharacterName()
}
