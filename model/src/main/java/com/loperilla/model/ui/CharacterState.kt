package com.loperilla.model.ui

import com.loperilla.model.quote.Quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.ui
 * Created By Manuel Lopera on 1/5/23 at 19:13
 * All rights reserved 2023
 */
sealed class CharacterState {
    object Loading : CharacterState()
    object CharacterView : CharacterState()
    data class ResultSearch(val resultList: List<Quote>) : CharacterState()
}
