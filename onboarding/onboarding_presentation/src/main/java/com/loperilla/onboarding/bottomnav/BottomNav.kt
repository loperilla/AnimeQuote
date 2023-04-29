package com.loperilla.onboarding.bottomnav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.loperilla.core_ui.util.UiNavEvent.Navigate

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.bottomnav
 * Created By Manuel Lopera on 29/4/23 at 13:11
 * All rights reserved 2023
 */

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    navClickEvent: (Navigate) -> Unit
) {

    NavigationBar {
        NavBarItems.BarItems.forEach { navItem ->

            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navClickEvent(Navigate(navItem.route))
                },

                icon = {
                    Icon(imageVector = navItem.image, contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }
}