package com.loperilla.composeanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loperilla.core_ui.ComposeAnimeTheme
import com.loperilla.core_ui.LOW
import com.loperilla.core_ui.input.SearchField
import com.loperilla.core_ui.routes.Routes.ANIME
import com.loperilla.core_ui.routes.Routes.CHARACTER
import com.loperilla.core_ui.routes.Routes.HOME
import com.loperilla.model.ui.AnimeState
import com.loperilla.onboarding.anime.AnimeViewModel
import com.loperilla.onboarding.anime.SearchAnime
import com.loperilla.onboarding.bottomnav.BottomNavigationBar
import com.loperilla.onboarding.character.CharacterScreen
import com.loperilla.onboarding.character.CharacterViewModel
import com.loperilla.onboarding.home.HomeScreen
import com.loperilla.onboarding.home.HomeViewModel
import com.loperilla.onboarding.home.QuotePagingList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimeTheme {
                val navController: NavHostController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
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
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            currentRoute ?: HOME
                        ) { navUi ->
                            navController.navigate(navUi.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }

                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = HOME,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(HOME) {
                            val homeViewModel = hiltViewModel<HomeViewModel>()

                            val randomQuotes =
                                homeViewModel.getPagingQuotes().collectAsLazyPagingItems()
                            HomeScreen(
                                randomQuotes
                            )
                        }

                        composable(CHARACTER) {
                            val characterViewModel = hiltViewModel<CharacterViewModel>()
                            val characterState by characterViewModel.characterState.collectAsStateWithLifecycle()
                            val currentInputValue by characterViewModel.searchText.collectAsStateWithLifecycle()
                            val characterList by characterViewModel.characterList.collectAsStateWithLifecycle()
                            val isSearching by characterViewModel.isSearching.collectAsStateWithLifecycle()

                            Column {
                                SearchField(
                                    textValue = currentInputValue,
                                    placeHolderText = "Search your anime",
                                    onTextChange = characterViewModel::onSearchTextChange,
                                    isInputFocusedListener = characterViewModel::onInputFocused
                                )
                                Spacer(modifier = Modifier.height(LOW))
                                CharacterScreen(
                                    modifier = Modifier
                                        .weight(1f),
                                    characterState = characterState,
                                    characterList,
                                    isSearching,
                                    onLoadingState = characterViewModel::getAllCharacter,
                                    onSelectCharacter = characterViewModel::selectCharacter
                                )
                            }
                        }

                        composable(ANIME) {
                            val animeViewModel = hiltViewModel<AnimeViewModel>()
                            val animeState by animeViewModel.animeState.collectAsStateWithLifecycle()
                            val currentInputValue by animeViewModel.searchText.collectAsStateWithLifecycle()
                            val animeList by animeViewModel.animeList.collectAsStateWithLifecycle()
                            val isSearching by animeViewModel.isSearching.collectAsStateWithLifecycle()
                            val quotes = animeViewModel.quotes.collectAsLazyPagingItems()
                            Column {
                                SearchField(
                                    textValue = currentInputValue,
                                    placeHolderText = "Search your anime",
                                    onTextChange = animeViewModel::onSearchTextChange,
                                    isInputFocusedListener = animeViewModel::onInputFocused
                                )
                                Spacer(modifier = Modifier.height(LOW))
                                when (animeState) {
                                    AnimeState.AnimeView -> {
                                        if (isSearching) {
                                            Box(modifier = Modifier.fillMaxSize()) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.align(Alignment.Center)
                                                )
                                            }
                                        } else {
                                            SearchAnime(
                                                modifier = Modifier,
                                                animeList = animeList,
                                                onSelectedAnime = {
                                                    animeViewModel.selectAnime(it)
                                                }
                                            )
                                        }

                                    }

                                    AnimeState.Loading -> {
                                        animeViewModel.getAllAnime()
                                    }

                                    AnimeState.ResultSearch -> {
                                        QuotePagingList(
                                            Modifier,
                                            quotes
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
