package com.example.composeexercise2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.widgets.SimpleAppBar
import com.example.composeexercise2.widgets.ImageRow
import com.example.composeexercise2.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().find { it.id == movieId }
    if (movie == null) {
        Text(text = "Error: no movie selected")
    } else {
        Column {
            SimpleAppBar(navController = navController, label = movie.title)
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

