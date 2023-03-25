package com.example.composeexercise2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.getMovies

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().find { it.id == movieId }
    if (movie == null) {
        Text(text = "Error: no movie selected")
    } else {
        Column {
            DetailsAppBar(navController = navController, movieName = movie.title)
            MovieRow(movie = movie)
            Text(text = "Movie Images",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp))
            ImageRow(movie = movie)
        }
    }
}

@Composable
fun DetailsAppBar(navController: NavController, movieName: String) {
    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",
            modifier = Modifier.clickable(
                onClick = {
                    navController.popBackStack()
                })
            )
            Text(movieName, fontSize = 24.sp)
        }
    }
}

@Composable
fun ImageRow(movie: Movie) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        items(movie.images) { movieImage -> 
            Card(modifier = Modifier.fillMaxHeight(1f)) {
                AsyncImage(
                    model = movieImage,
                    contentDescription = "Scene from ${movie.title}",
                    modifier = Modifier.fillMaxHeight(1f),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}