package com.example.composeexercise2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.widgets.ScrollableMovieColumn
import com.example.composeexercise2.widgets.SimpleAppBar

@Composable
fun FavoriteScreen(navController: NavController) {
    Column {
        SimpleAppBar(navController = navController, label = "Favorites")
        val movies = getMovies()
        val favMovies = ArrayList<Movie>()
        favMovies.add(movies[2])
        favMovies.add(movies[4])
        ScrollableMovieColumn(navController, favMovies)
    }
}

