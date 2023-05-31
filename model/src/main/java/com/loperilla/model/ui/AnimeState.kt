package com.loperilla.model.ui

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.ui
 * Created By Manuel Lopera on 1/5/23 at 11:41
 * All rights reserved 2023
 */
sealed class AnimeState {
    object Loading : AnimeState()
    object AnimeView : AnimeState()
    object ResultSearch : AnimeState()
}
