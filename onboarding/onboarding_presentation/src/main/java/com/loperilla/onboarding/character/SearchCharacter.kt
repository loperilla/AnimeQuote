package com.loperilla.onboarding.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.sp
import com.loperilla.core_ui.LOW
import com.loperilla.model.quote.Character

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.character
 * Created By Manuel Lopera on 1/5/23 at 18:51
 * All rights reserved 2023
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchCharacter(
    modifier: Modifier = Modifier,
    characterList: List<Character>,
    onSelectedCharacter: (Character) -> Unit
) {
    val controller =
        LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            characterList.size
        ) {
            Text(
                text = characterList[it].name,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(LOW)
                    .clickable {
                        onSelectedCharacter(characterList[it])
                        controller?.hide()
                        focusManager.clearFocus(true)
                    }
            )
        }
    }
}
