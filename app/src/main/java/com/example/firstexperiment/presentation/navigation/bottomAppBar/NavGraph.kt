package com.example.firstexperiment.presentation.navigation.bottomAppBar

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import androidx.navigation.navArgument
import com.example.firstexperiment.presentation.screens.HomeScreen
import com.example.firstexperiment.presentation.viewModels.actorList.ActorsScreen
import com.example.firstexperiment.presentation.viewModels.movieList.MoviesScreen
import com.example.firstexperiment.presentation.screens.UserProfileScreen
import com.example.firstexperiment.presentation.util.Screen
import com.example.firstexperiment.presentation.viewModels.actorDetails.ActorDetailsScreen
import com.example.firstexperiment.presentation.viewModels.movieDetails.MovieDetailsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {

    AnimatedNavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        // Home Screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        // Actor Screens
        composable(route = Screen.ActorsScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(50))
            }) {
            ActorsScreen(navController)
        }

        composable(route = Screen.ActorDetailsScreen.route + "/{actorID}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(50))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            arguments = listOf(navArgument("actorID") { type = NavType.StringType })
        ) {
            ActorDetailsScreen(navController)
        }


        // Movie Screens
        composable(
            route = Screen.MoviesScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(50))
            }
        ) {
            MoviesScreen(navController)
        }

        composable(route = Screen.MovieDetailsScreen.route + "/{movieID}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(50))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -250 },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(50))
            },
            arguments = listOf(navArgument("movieID") { type = NavType.StringType })
        ) {
            MovieDetailsScreen(navController)
        }


        // User Profile Screen
        composable(route = Screen.UserProfileScreen.route) {
            UserProfileScreen()
        }
    }
}