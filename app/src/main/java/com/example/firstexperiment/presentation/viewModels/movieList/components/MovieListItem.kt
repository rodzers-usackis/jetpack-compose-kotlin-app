package com.example.firstexperiment.presentation.viewModels.movieList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.firstexperiment.R
import com.example.firstexperiment.domain.model.Movie
import java.time.LocalDate

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

    Card(modifier = Modifier
        .height(100.dp)
        .fillMaxWidth(), elevation = 10.dp) {

        Row(modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(movie) }) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.poster)
                    .size(Size.ORIGINAL)
                    .build()
            )

            when (painter.state) {
                is AsyncImagePainter.State.Loading ->  {
                    Column(modifier = Modifier.fillMaxSize().weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
                }
                is AsyncImagePainter.State.Error -> {
                    Image(
                        painter = painterResource(id = R.drawable.ic_movie_poster_placeholder),
                        contentDescription = "Placeholder Icon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = painter,
                        contentDescription = "Movie Poster Image",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    )
                }
                else -> {}
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxSize()
                    .weight(4f), verticalArrangement = Arrangement.Center
            ) {
                Text(text = movie.title, fontSize = 20.sp)

                val date = LocalDate.parse(movie.releaseDate)


                Text(
                    text = "" + date.year.toString() + "  2h 55m  " + movie.movieRating,
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 3.dp)
                )

                Row() {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Movie Rating Icon",
                        tint = Color(246, 190, 0, 255),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = " ${movie.criticsScore}",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(
                            Alignment.CenterVertically
                        )
                    )
                }
            }

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                Image(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "View Details Button",
                    modifier = Modifier
                        .weight(2f)
                        .size(30.dp)
                )
            }
        }
    }
}

