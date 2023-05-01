package com.loperilla.onboarding.anime

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
import com.loperilla.model.quote.Anime

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.anime
 * Created By Manuel Lopera on 1/5/23 at 10:42
 * All rights reserved 2023
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAnime(
    modifier: Modifier = Modifier,
    animeList: List<Anime>,
    onSelectedAnime: (Anime) -> Unit
) {
    val controller =
        LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            animeList.size
        ) {
            Text(
                text = animeList[it].name,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(LOW)
                    .clickable {
                        onSelectedAnime(animeList[it])
                        controller?.hide()
                        focusManager.clearFocus(true)
                    }
            )
        }
    }
}
