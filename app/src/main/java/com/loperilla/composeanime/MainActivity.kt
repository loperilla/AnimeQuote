package com.loperilla.composeanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loperilla.composeanime.ui.theme.ComposeAnimeTheme
import com.loperilla.onboarding.home.HomeScreen
import com.loperilla.onboarding.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimeTheme {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                homeViewModel.getRandomQuotes()
                val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()

                HomeScreen(
                    homeState
                )
            }
        }
    }
}
