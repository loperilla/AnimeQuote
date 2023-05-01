package com.loperilla.onboarding.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.loperilla.model.quote.Character
import com.loperilla.model.ui.CharacterState
import com.loperilla.onboarding.common.QuoteList

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.character
 * Created By Manuel Lopera on 1/5/23 at 18:51
 * All rights reserved 2023
 */

@Composable
fun CharacterScreen(
    modifier: Modifier,
    characterState: CharacterState,
    characterList: List<Character>,
    isSearching: Boolean,
    onLoadingState: () -> Unit,
    onSelectCharacter: (Character) -> Unit
) {
    when (characterState) {
        CharacterState.CharacterView -> {
            if (isSearching) {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                SearchCharacter(
                    modifier = modifier,
                    characterList = characterList,
                    onSelectedCharacter = {
                        onSelectCharacter(it)
                    }
                )
            }
        }

        CharacterState.Loading -> {
            onLoadingState()
        }

        is CharacterState.ResultSearch -> {
            QuoteList(
                modifier,
                characterState.resultList
            )
        }
    }
}