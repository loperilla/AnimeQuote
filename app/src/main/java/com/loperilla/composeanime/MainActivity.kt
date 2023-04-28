package com.loperilla.composeanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loperilla.core_ui.ComposeAnimeTheme
import com.loperilla.core_ui.LOW
import com.loperilla.onboarding.home.HomeScreen
import com.loperilla.onboarding.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimeTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("ComposeAnime")
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                        )
                    }
                ) { paddingValues ->
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()

                    HomeScreen(
                        homeState,
                        homeViewModel::getRandomQuotes,
                        modifier = Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}
