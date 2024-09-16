package com.example.firstexperiment.presentation.viewModels.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.MovieCast
import com.example.firstexperiment.presentation.util.Screen
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movie?.let { mov ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Text(
                        text = mov.title,
                        style = MaterialTheme.typography.h4,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.h5
                    )
                    FlowRow(modifier = Modifier.fillMaxWidth()) {
                        mov.genres.forEach { genre ->
                            Button(onClick = {}, modifier = Modifier.padding(end = 10.dp)) {
                                Text(text = genre)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Movie Cast",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    movieCast(movieCast = mov.movieCast, navController)
                    Text(
                        text = "Plot Summary",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = mov.plotSummary,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun movieCast(movieCast: List<MovieCast>, navController: NavController) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxWidth()) {
            items(movieCast, itemContent = { actor ->


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(actor.portrait)
                            .size(Size.ORIGINAL)
                            .build()
                    )

                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            Column(modifier = Modifier
                                .height(240.dp)
                                .width(180.dp)
                                .padding(end = 10.dp)
                                .clip(shape = MaterialTheme.shapes.small),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is AsyncImagePainter.State.Error -> {
                            Image(
                                painter = painterResource(id = com.example.firstexperiment.R.drawable.ic_movie_poster_placeholder),
                                contentDescription = "Loading Icon",
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(180.dp)
                                    .padding(end = 10.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable { navController.navigate(Screen.ActorDetailsScreen.route + "/${actor.id}") }
                            )
                        }
                        is AsyncImagePainter.State.Success -> {
                            Image(
                                painter = painter,
                                contentDescription = "Actor Portrait Image",
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(180.dp)
                                    .padding(end = 10.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable { navController.navigate(Screen.ActorDetailsScreen.route + "/${actor.id}") }
                            )
                        }
                        else -> {}
                    }

                    Text(
                        text = actor.firstName + " " + actor.lastName,
                        modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp),
                        fontSize = 16.sp
                    )
                }
            })
        }
    }
}