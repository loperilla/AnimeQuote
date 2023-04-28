package com.loperilla.onboarding.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loperilla.core_ui.LOW
import com.loperilla.model.database.ShoppingItem
import com.loperilla.onboarding.home.model.HomeState

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding.home
 * Created By Manuel Lopera on 24/4/23 at 19:50
 * All rights reserved 2023
 */

@Composable
fun HomeScreen(homeState: HomeState, modifier: Modifier) {
    if (homeState.shoppingListItem.isEmpty() || homeState.errorMessage.isNotEmpty()) {
        Text(text = "lista vacía")
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(homeState.shoppingListItem.size) {
                HomeItemView(homeState.shoppingListItem[it])
            }
        }
    }
}

@Composable
fun HomeItemView(
    item: ShoppingItem
) {
    Column {
        Text(
            text = item.title,
            color = Color.Blue,
            modifier = Modifier
                .padding(LOW)
        )

        Text(
            text = item.date,
            color = Color.Green,
            modifier = Modifier
                .padding(LOW)
        )
    }
}