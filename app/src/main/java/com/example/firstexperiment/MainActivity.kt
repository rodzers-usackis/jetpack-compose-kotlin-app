package com.example.firstexperiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstexperiment.presentation.navigation.bottomAppBar.NavGraph
import com.example.firstexperiment.presentation.navigation.bottomAppBar.NavBarItems
import com.example.firstexperiment.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import com.example.firstexperiment.presentation.ui.theme.FirstExperimentTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstExperimentTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent() {
    val navController: NavHostController = rememberAnimatedNavController()

    Scaffold(
        topBar = { TopNavBar(navController = navController) },
        // Why was this such a pain in the ass to find documentation for.
        content = { paddingValues -> Column(modifier = Modifier.padding(paddingValues)) {
            NavGraph(navController = navController)
        } },
        bottomBar = { BottomNavBar(navController = navController) }
        )
}

@Composable
fun TopNavBar(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val regularScreens = listOf(
        Screen.ActorsScreen,
        Screen.MoviesScreen,
        Screen.HomeScreen,
        Screen.UserProfileScreen
    )

    val bottomBarDestination = regularScreens.any { it.route == currentRoute }

    if (bottomBarDestination) {
        TopAppBar(
            title = { Text(text = "CinemaDB") },
            navigationIcon = {
                IconButton(onClick = { navController.previousBackStackEntry }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            }
        )
    } else {
        TopAppBar (
            title = { if (currentRoute.toString().contains("view_movie_details_screen")) {
                        Text(text = "Movie Detail")
                    } else {
                        Text(text = "Actor Detail")
                    }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            }
        )
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {

    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.NavBarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        // Restores state when re-entering the view.
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}