package com.loperilla.onboarding.bottomnav.model

import androidx.compose.ui.graphics.vector.ImageVector

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.bottomnav.model
 * Created By Manuel Lopera on 29/4/23 at 13:23
 * All rights reserved 2023
 */
data class NavBarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)
