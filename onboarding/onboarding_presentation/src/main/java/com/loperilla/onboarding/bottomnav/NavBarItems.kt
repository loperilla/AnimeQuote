package com.loperilla.onboarding.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import com.loperilla.core_ui.routes.Routes.ANIME
import com.loperilla.core_ui.routes.Routes.CHARACTER
import com.loperilla.core_ui.routes.Routes.HOME
import com.loperilla.onboarding.bottomnav.model.NavBarItem
import java.util.Locale

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.bottomnav
 * Created By Manuel Lopera on 29/4/23 at 13:23
 * All rights reserved 2023
 */
object NavBarItems {
    val BarItems = listOf(
        NavBarItem(
            title = HOME.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            image = Icons.Filled.Home,
            route = HOME
        ),
        NavBarItem(
            title = ANIME.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            image = Icons.Filled.Face,
            route = ANIME
        ),
        NavBarItem(
            title = CHARACTER.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            image = Icons.Filled.Favorite,
            route = CHARACTER
        )
    )
}