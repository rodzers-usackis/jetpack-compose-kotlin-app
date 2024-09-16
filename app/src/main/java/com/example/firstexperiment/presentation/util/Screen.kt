package com.example.firstexperiment.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home_screen")
    object MoviesScreen: Screen(route = "view_movies_screen")
    object MovieDetailsScreen: Screen(route = "view_movie_details_screen")
    object ActorsScreen: Screen(route = "view_actors_screen")
    object ActorDetailsScreen: Screen(route = "view_actor_details_screen")
    object UserProfileScreen: Screen(route = "view_user_profile_screen")
}