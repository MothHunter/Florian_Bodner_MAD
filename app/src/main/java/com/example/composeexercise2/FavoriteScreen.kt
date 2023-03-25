package com.example.composeexercise2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    Column {
        BasicAppBar(navController = navController, label = "Favorites")
        val movies = getMovies()
        val favMovies = ArrayList<Movie>()
        favMovies.add(movies[2])
        favMovies.add(movies[4])
        LazyColumn {
            items(favMovies) { movie ->
                MovieRow(movie) { movieID ->
                    navController.navigate(route = "detailscreen/$movieID")
                }
            }
        }
    }
}