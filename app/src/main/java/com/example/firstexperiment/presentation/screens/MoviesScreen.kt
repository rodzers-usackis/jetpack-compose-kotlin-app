package com.example.firstexperiment.presentation.viewModels.movieList

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firstexperiment.presentation.util.Screen
import com.example.firstexperiment.presentation.viewModels.movieList.components.MovieListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var refreshing by remember { mutableStateOf(false)}

    // Coroutine
    LaunchedEffect(key1 = refreshing, block = {
        if(refreshing) {
            // simulates long fetch time
            delay(3000)
            viewModel.refreshMovies()
            Log.d("Refresh Movies: ", "Refreshing Movies")
            refreshing = false
        }
    })

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(all = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(state.movies) { movie ->
                        MovieListItem(movie = movie, onItemClick = { navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.id}") })
                    }
                }

                if(state.error.isNotBlank()) {
                    Text(text = state.error, color = MaterialTheme.colors.error, textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center))
                }

                if(state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        })
}