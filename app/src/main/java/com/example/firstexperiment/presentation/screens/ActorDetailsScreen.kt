package com.example.firstexperiment.presentation.viewModels.actorDetails

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
import com.example.firstexperiment.R
import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.MoviesActedIn
import com.example.firstexperiment.presentation.util.Screen

@Composable
fun ActorDetailsScreen(
    navController: NavController,
    viewModel: ActorDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.actor?.let { act ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(act.portrait)
                            .size(Size.ORIGINAL)
                            .build()
                    )

                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            Column(modifier = Modifier
                                .height(240.dp)
                                .width(180.dp)
                                .padding(end = 10.dp)
                                .clip(shape = MaterialTheme.shapes.small)
                                .clickable {},
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is AsyncImagePainter.State.Error -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_movie_poster_placeholder),
                                contentDescription = "Placeholder Image",
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(180.dp)
                                    .padding(end = 10.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable {}
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
                                    .clickable {}
                            )
                        }
                        else -> {}
                    }
                    Text(
                        text = act.firstName + " " + act.lastName,
                        style = MaterialTheme.typography.h4,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Personal Details",
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = "Born: " + act.dateOfBirth + "\n" + act.cityBorn + ", " + act.countryBorn,
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Movies Acted In",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    moviesActedIn(
                        moviesActedIn = act.moviesActedIn,
                        navController
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
fun moviesActedIn(moviesActedIn: List<MoviesActedIn>, navController: NavController) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxWidth()) {
            items(moviesActedIn, itemContent = { movie ->


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(movie.poster)
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
                                painter = painterResource(id = R.drawable.ic_movie_poster_placeholder),
                                contentDescription = "Placeholder Image",
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(180.dp)
                                    .padding(end = 10.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable { navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.id}") }
                            )
                        }
                        is AsyncImagePainter.State.Success -> {
                            Image(
                                painter = painter,
                                contentDescription = "Movie Poster Image",
                                modifier = Modifier
                                    .height(240.dp)
                                    .width(180.dp)
                                    .padding(end = 10.dp)
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable { navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.id}") }
                            )
                        }
                        else -> {}
                    }

                    Text(
                        text = movie.title,
                        modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp),
                        fontSize = 16.sp
                    )
                }
            })
        }
    }
}