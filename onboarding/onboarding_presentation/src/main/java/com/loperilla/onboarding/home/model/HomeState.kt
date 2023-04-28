package com.loperilla.onboarding.home.model

import com.loperilla.model.database.ShoppingItem

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding.home.model
 * Created By Manuel Lopera on 27/4/23 at 19:43
 * All rights reserved 2023
 */

data class HomeState(
    var shoppingListItem: List<ShoppingItem> = emptyList(),
    var errorMessage: String = ""
)
