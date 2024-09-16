package com.example.firstexperiment.presentation.navigation.bottomAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hail
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Person

object NavBarItems {
    val NavBarItems = listOf(
        NavigationBarItem(
            title = "Home",
            image = Icons.Default.Home,
            route = "home_screen"
        ),
        NavigationBarItem(
            title = "Movies",
            image = Icons.Default.MovieFilter,
            route = "view_movies_screen"
        ),
        NavigationBarItem(
            title = "Actors",
            image = Icons.Default.Hail,
            route = "view_actors_screen"
        ),
        NavigationBarItem(
            title = "Profile",
            image = Icons.Default.Person,
            route = "view_user_profile_screen"
        )
    )
}