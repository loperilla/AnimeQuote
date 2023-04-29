package com.loperilla.composeanime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.loperilla.core_ui.ComposeAnimeTheme
import com.loperilla.core_ui.routes.Routes.ANIME
import com.loperilla.core_ui.routes.Routes.CHARACTER
import com.loperilla.core_ui.routes.Routes.HOME
import com.loperilla.onboarding.bottomnav.BottomNavigationBar
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
                            val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()

                            HomeScreen(
                                homeState,
                                homeViewModel::getRandomQuotes
                            )
                        }

                        composable(CHARACTER) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Red)
                            )
                        }

                        composable(ANIME) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Green)
                            )
                        }
                    }
                }
            }
        }
    }
}
