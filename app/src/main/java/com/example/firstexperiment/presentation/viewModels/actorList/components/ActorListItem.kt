package com.example.firstexperiment.presentation.viewModels.actorList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.firstexperiment.R
import com.example.firstexperiment.domain.model.Actor

@Composable
fun ActorListItem(
    actor: Actor,
    onItemClick: (Actor) -> Unit,
) {

    Card(modifier = Modifier
        .height(100.dp)
        .fillMaxWidth(), elevation = 10.dp) {

        Row(modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(actor) }) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(actor.portrait)
                    .size(Size.ORIGINAL)
                    .build()
            )

            when (painter.state) {
                is AsyncImagePainter.State.Loading ->  { CircularProgressIndicator() }
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
                        contentDescription = "Actor Portrait Image",
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
                Text(text = actor.firstName + " ${actor.lastName}", fontSize = 20.sp)


                Text(
                    text = "${actor.cityBorn}," + " ${actor.countryBorn}",
                    fontSize = 13.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
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