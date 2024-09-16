package com.example.firstexperiment.presentation.viewModels.actorList

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
import com.example.firstexperiment.presentation.viewModels.actorList.components.ActorListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun ActorsScreen(
    navController: NavController,
    viewModel: ActorListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var refreshing by remember { mutableStateOf(false)}

    LaunchedEffect(key1 = refreshing, block = {
        if(refreshing) {
            delay(3000)
            viewModel.refreshActors()
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
                items(state.actors) { actor ->
                    ActorListItem(actor = actor, onItemClick = { navController.navigate(Screen.ActorDetailsScreen.route + "/${actor.id}") })
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
